package gr.ntua.imu.topics.analyzer;

import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.StringArrayIterator;
import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.types.Alphabet;
import cc.mallet.types.IDSorter;
import cc.mallet.types.InstanceList;
import gr.ntua.imu.topics.data.FileSource;
import gr.ntua.imu.topics.model.Token;
import gr.ntua.imu.topics.model.TokenImpl;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author KostasChr
 */
public class AnalyzerImpl implements Analyzer, Serializable {

    private Pipe pipe;
    private Integer numberOfTopics;
    private Integer numberOfIterations;
    private Double alpha;
    private Double beta;
    private ParallelTopicModel topicModel;
    private FileSource fileSource;
    private ArrayList<TreeSet<IDSorter>> topicSortedWords;
    private double[] topicDistribution;
    private Alphabet dataAlphabet;


    public String getFileUpload() {
        return this.fileSource.toString();
    }

    public void setFileUpload(String path) {
        FileSource fileSource= this.fileSource;
        fileSource.setFilePath(path);
    }

    @Override
    public void loadTrainSet() {
        try {
            setPipe(buildPipe());
            getFileSource().readDocuments();
            String[] documents = getFileSource().getDocuments().toArray(new String[]{});
            StringArrayIterator iterator = new StringArrayIterator(documents);
            InstanceList instances = new InstanceList(this.getPipe());
            instances.addThruPipe(iterator);
            setTopicModel(new ParallelTopicModel(getNumberOfTopics(), getAlpha(), getBeta()));
            getTopicModel().addInstances(instances);
            setDataAlphabet(instances.getDataAlphabet());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Pipe buildPipe() {

        ArrayList pipeList = new ArrayList();

        pipeList.add(new Input2CharSequence("UTF-8"));

        Pattern tokenPattern =
                Pattern.compile("[\\p{L}\\p{N}_]{3,}+");
        pipeList.add(new CharSequence2TokenSequence(tokenPattern));
        pipeList.add(new TokenSequenceLowercase());
        pipeList.add(new TokenSequenceRemoveStopwords(false, false));
        pipeList.add(new TokenSequence2FeatureSequence());

        return new SerialPipes(pipeList);
    }

    @Override
    public void estimate() {
        getTopicModel().setTopicDisplay(100, 20);
        getTopicModel().setNumIterations(getNumberOfIterations());
        try {
            getTopicModel().estimate();
            topicSortedWords = getTopicModel().getSortedWords();
            topicDistribution = getTopicModel().getTopicProbabilities(0);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PriorityQueue<Token> getWordsForTopic(Integer topicId) {

        Comparator<Token> comparator = new Comparator<Token>() {
            @Override
            public int compare(Token token, Token token2) {
                return Double.compare(token2.getProbability(), token.getProbability());
            }
        };
        PriorityQueue<Token> queue = new PriorityQueue<Token>(11, comparator);
        Iterator<IDSorter> iterator = topicSortedWords.get(topicId).iterator();

        while (iterator.hasNext()) {
            IDSorter idCountPair = iterator.next();
            Token token = new TokenImpl((String) getDataAlphabet().lookupObject(idCountPair.getID()), idCountPair.getWeight());
            queue.add(token);
        }

        return queue;
    }

    @Override
    public Integer getSize() {
        return getTopicModel().getNumTopics();
    }

    public Pipe getPipe() {
        return pipe;
    }

    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
    }

    public Integer getNumberOfTopics() {
        return numberOfTopics;
    }

    public void setNumberOfTopics(Integer numberOfTopics) {
        this.numberOfTopics = numberOfTopics;
    }

    public Integer getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(Integer numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public ParallelTopicModel getTopicModel() {
        return topicModel;
    }

    public void setTopicModel(ParallelTopicModel topicModel) {
        this.topicModel = topicModel;
    }

    public FileSource getFileSource() {
        return fileSource;
    }

    public void setFileSource(FileSource fileSource) {
        this.fileSource = fileSource;
    }

    public ArrayList<TreeSet<IDSorter>> getTopicSortedWords() {
        return topicSortedWords;
    }

    public void setTopicSortedWords(ArrayList<TreeSet<IDSorter>> topicSortedWords) {
        this.topicSortedWords = topicSortedWords;
    }

    public double[] getTopicDistribution() {
        return topicDistribution;
    }

    public void setTopicDistribution(double[] topicDistribution) {
        this.topicDistribution = topicDistribution;
    }

    public Alphabet getDataAlphabet() {
        return dataAlphabet;
    }

    public void setDataAlphabet(Alphabet dataAlphabet) {
        this.dataAlphabet = dataAlphabet;
    }
}
