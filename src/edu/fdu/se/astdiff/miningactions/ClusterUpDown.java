package edu.fdu.se.astdiff.miningactions;

import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.tree.Tree;
import edu.fdu.se.astdiff.miningactions.Body.*;
import edu.fdu.se.astdiff.miningactions.bean.MiningActionData;
import edu.fdu.se.astdiff.miningactions.statement.*;
import edu.fdu.se.astdiff.miningactions.util.AstRelations;
import org.eclipse.jdt.core.dom.ASTNode;

/**
 * Created by huangkaifeng on 2018/2/2.
 * Statement/Declaration/控制流的子结构
 */
public class ClusterUpDown extends AbstractCluster{

    public ClusterUpDown(Class mClazz, MiningActionData mminingActionData) {
        super(mClazz, mminingActionData);
    }

    public void doClusterUpDown() {
        int actionCnt = this.actionList.size();
        for(int index =0; index!=actionCnt;index++){
            Action a = this.actionList.get(index);
            if (fp.mGeneratingActionsData.getAllActionMap().get(a) == 1) {
                continue;
            }
            Tree insNode = (Tree) a.getNode();
            if(processBigAction(a,insNode.getAstNode().getNodeType())==1) {

            }
        }
    }


    public int processBigAction(Action a,int type) {
        int res = 0;
        switch (type) {
            // 外面
            case ASTNode.TYPE_DECLARATION:
                MatchClass.matchClassDeclaration(fp, a);
                break;
            case ASTNode.FIELD_DECLARATION:
                MatchFieldDeclaration.matchFieldDeclaration(fp, a);
                break;
            case ASTNode.INITIALIZER:
                MatchInitializerBlock.matchInitializerBlock(fp, a);
                break;
            case ASTNode.METHOD_DECLARATION:
                MatchMethod.matchMethdDeclaration(fp, a);
                break;
            case ASTNode.ENUM_DECLARATION:
                //// TODO: 2018/2/8
                break;

            // 里面
            case ASTNode.ASSERT_STATEMENT:
                MatchAssert.matchAssert(fp,a);
                break;
            case ASTNode.IF_STATEMENT:
                MatchIfElse.matchIf(fp, a);
                break;
            case ASTNode.BLOCK:
                MatchBlock.matchBlock(fp, a);
                break;
            case ASTNode.BREAK_STATEMENT:
                MatchControlStatements.matchBreakStatements(fp,a);
                break;
            case ASTNode.CONTINUE_STATEMENT:
                MatchControlStatements.matchContinueStatements(fp,a);
            case ASTNode.RETURN_STATEMENT:
                MatchReturnStatement.matchReturnStatement(fp, a);
                break;
            case ASTNode.FOR_STATEMENT:
                //增加for语句
                MatchForStatement.matchForStatement(fp, a);
                break;
            case ASTNode.ENHANCED_FOR_STATEMENT:
                //增加for语句
                MatchForStatement.matchEnhancedForStatement(fp, a);
                break;
            case ASTNode.WHILE_STATEMENT:
                //增加while语句
                MatchWhileStatement.matchWhileStatement(fp, a);
                break;
            case ASTNode.DO_STATEMENT:
                //增加do while语句
                MatchWhileStatement.matchDoStatement(fp, a);
                break;
            case ASTNode.TRY_STATEMENT:
                MatchTry.matchTry(fp, a);
                break;
            case ASTNode.THROW_STATEMENT:
                MatchTry.matchThrowStatement(fp, a);
                break;
            case ASTNode.CATCH_CLAUSE:
                MatchTry.matchCatchClause(fp,a);
                break;
            case ASTNode.VARIABLE_DECLARATION_STATEMENT:
                MatchVariableDeclarationExpression.matchVariableDeclaration(fp, a);
                break;
            case ASTNode.EXPRESSION_STATEMENT:
                if (AstRelations.isFatherXXXStatement(a, ASTNode.IF_STATEMENT) && a.getNode().getParent().getChildPosition(a.getNode()) == 2) {
                    MatchIfElse.matchElse(fp, a);
                } else {
                    MatchExpressionStatement.matchExpression(fp, a);
                }
                break;

            case ASTNode.SYNCHRONIZED_STATEMENT:
                MatchSynchronized.matchSynchronized(fp, a);
                break;
            case ASTNode.SWITCH_STATEMENT:
                MatchSwitch.matchSwitch(fp, a);
                break;
            case ASTNode.SWITCH_CASE:
                MatchSwitch.matchSwitchCase(fp, a);
                break;
            case ASTNode.EMPTY_STATEMENT:
                break;
            case ASTNode.LABELED_STATEMENT:
                break;
            case ASTNode.TYPE_DECLARATION_STATEMENT:
                break;
            //                case ASTNode.CONDITIONALEXPRESSION:
//                    MatchConditionalExpression.matchConditionalExpression(fp, a, type);
//                    break;

            default:
                res =1;
                break;
        }
        return  res;
    }
}