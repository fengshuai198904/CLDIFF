package edu.fdu.se.astdiff.link.similarity;

import com.github.gumtreediff.tree.Tree;
import edu.fdu.se.astdiff.preprocessingfile.FilePairPreDiff;
import edu.fdu.se.astdiff.preprocessingfile.data.PreprocessedData;
import edu.fdu.se.astdiff.treegenerator.JavaParserTreeGenerator;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        Test i = new Test();
        i.runBatchTest();
    }

    /**
     * 使用修改简化之后的流程，测试多个文件的功能
     */
    private void runBatchTest() {
        String batchTestFilePath = "D:\\Workspace\\CodeDiff\\test_batch_simple_action";
        File currdir = new File(batchTestFilePath + "\\curr");
        File[] files = currdir.listFiles();
        String outputDir = "test";
        try{
            for (File currf1 : files) {
                String prevFile = batchTestFilePath + "\\prev\\" + currf1.getName();
                if(currf1.getName().startsWith("TestMove.java")) {
                    System.out.println(currf1.getName());
//                    doo(prevFile, currf1.getAbsolutePath(), outputDir);
                    testDoo(prevFile, currf1.getAbsolutePath(), outputDir);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Tree t;
//
//        t.getAstNode().getClass().getSimpleName()
    }

    //测试相似度检测测试
    private void testDoo(String filePrev, String fileCurr, String output){
        FilePairPreDiff psc = new FilePairPreDiff();
        psc.compareTwoFile(filePrev, fileCurr, output);
        PreprocessedData preData = psc.getPreprocessedData();
        JavaParserTreeGenerator jtg = new JavaParserTreeGenerator(preData.getSrcCu(),preData.getDstCu());

        TreeDistance treeDistance = new TreeDistance((Tree)jtg.src,(Tree)jtg.dst);
        float distance = treeDistance.calculateTreeDistance();
        System.out.println(distance);
    }

}