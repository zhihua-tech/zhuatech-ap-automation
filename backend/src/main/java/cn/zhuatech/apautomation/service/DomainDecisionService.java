/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apautomation.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class DomainDecisionService {
 public DecisionResult assess(DecisionRequest request) { double poGap=Math.abs(request.invoiceAmount()-request.purchaseOrderAmount());double receiptGap=Math.abs(request.invoiceAmount()-request.receiptAmount());double tolerance=Math.max(1,request.invoiceAmount()*0.001);int score=100;List<String> actions=new ArrayList<>();if(!request.vendorActive()){score-=40;actions.add("恢复或更换有效供应商");}if(request.duplicateInvoice()){score-=60;actions.add("阻断重复发票并调查");}if(!request.taxValidated()){score-=30;actions.add("完成发票税务校验");}if(poGap>tolerance){score-=20;actions.add("处理订单金额差异");}if(receiptGap>tolerance){score-=20;actions.add("处理收货金额差异");}return result(score,actions,"AUTO_MATCHED","EXCEPTION_REVIEW","PAYMENT_BLOCKED",Map.of("poVariance",poGap,"receiptVariance",receiptGap,"tolerance",tolerance)); }
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 public record DecisionRequest(
        @NotBlank String invoiceNo,
        @PositiveOrZero double purchaseOrderAmount,
        @PositiveOrZero double receiptAmount,
        @PositiveOrZero double invoiceAmount,
        boolean vendorActive,
        boolean duplicateInvoice,
        boolean taxValidated) {}
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}
