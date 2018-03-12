package edu.fdu.se.astdiff.miningoperationbean.base;


import edu.fdu.se.astdiff.miningactions.util.DownUpMatchUtil;
import edu.fdu.se.astdiff.miningactions.util.UpDownMatchUtil;
import edu.fdu.se.astdiff.miningoperationbean.ClusteredActionBean;
import edu.fdu.se.astdiff.miningoperationbean.OperationTypeConstants;

/**
 * Created by huangkaifeng on 2018/1/22.
 *
 *
 */
public abstract class StatementPlusChangeEntity extends ChangeEntity {

    public StatementPlusChangeEntity(ClusteredActionBean bean){
        super(bean);
    }


    public void appendListString(){
        if(this.clusteredActionBean.traverseType == ClusteredActionBean.TRAVERSE_UP_DOWN){
            UpDownMatchUtil.setChangePacket(this.clusteredActionBean);
        }else{
            DownUpMatchUtil.setChangePacket(this.clusteredActionBean);
        }
        this.changeType = this.clusteredActionBean.changePacket.getOperationType();
        if(this.changeType == OperationTypeConstants.MULTIPLE_EDIT){
            this.outputStringList.add(this.clusteredActionBean.changePacket.multiEditStr);
        }else{
            this.outputStringList.add(OperationTypeConstants.getKeyNameByValue(this.changeType));
        }
        this.outputStringList.add(this.changeEntity);
        this.outputStringList.add(OperationTypeConstants.getKeyNameByValue(this.clusteredActionBean.changePacket.getOperationSubEntity()));
        this.outputStringList.add(this.lineRangeStr);

    }




}