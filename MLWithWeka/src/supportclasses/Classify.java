package supportclasses;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayesMultinomialText;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;

public class Classify {
    private Instances training;
    private Instances testing;
    private Classifier classifier;

    public Classify() {
        training = null;
        testing = null;
        classifier = null;
    }

    public boolean loadModel(String path) {
        try {
            classifier = (Classifier) SerializationHelper.read(path);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Train(String path) {
        try {
            classifier = new NaiveBayesMultinomialText();
            ConverterUtils.DataSource trainingDataSource = new ConverterUtils.DataSource(path);
            training = trainingDataSource.getDataSet();

            if (training.classIndex() == -1) {
                training.setClassIndex(training.numAttributes() - 1);
            }

            classifier.buildClassifier(training);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean classify(String path) {
        if (classifier == null) {
            System.out.println("You have not trained the classifier yet.");
        } else {
            try {
                ConverterUtils.DataSource testingDataSource = new ConverterUtils.DataSource(path);
                testing = testingDataSource.getDataSet();

                if (testing.classIndex() == -1) {
                    testing.setClassIndex(testing.numAttributes() - 1);
                }

                for (int i = 0; i < testing.numInstances(); i++) {
                    double label = classifier.classifyInstance(testing.instance(i));
                    testing.instance(i).setClassValue(label);
                }

                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    public Instances getTraining() {
        return training;
    }

    public Instances getTesting() {
        return testing;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public String classificationsAsString() {
        String testClasses = "";
        if (testing == null) {
            System.out.println("No test set classified");
        } else {
            for (int i = 0; i < testing.numInstances(); i++) {
                Instance instance = testing.instance(i);
                String attributeValue = instance.classAttribute().value((int) instance.classValue());
                String article = "a";
                if(attributeValue.matches("^[aeiou].*$")){article = "an";} else {article = "a";}
                testClasses = testClasses.concat("This article is " +article +" " + attributeValue + " article." + "\n");
            }
        }

        return testClasses;
    }

    public void printTestClasses() {

        if (testing == null) {
            System.out.println("No test set classified");
        } else {
            for (int i = 0; i < testing.numInstances(); i++) {
                Instance instance = testing.instance(i);
                String attributeValue = instance.classAttribute().value((int) instance.classValue());
                System.out.println("Article " + (i + 1) + " is a " + attributeValue + " article.");
            }
        }
    }
}
