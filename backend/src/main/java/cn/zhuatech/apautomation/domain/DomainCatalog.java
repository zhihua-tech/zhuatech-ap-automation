/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apautomation.domain;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    public DomainCatalog() {
        actions.put("MATCH", new WorkflowAction("MATCH", "执行三单匹配", List.of("草稿"), "待审批", "OPERATOR"));
        actions.put("APPROVE", new WorkflowAction("APPROVE", "批准付款", List.of("待审批"), "已批准", "ADMIN"));
        actions.put("PAY", new WorkflowAction("PAY", "确认付款", List.of("已批准"), "已付款", "ADMIN"));
    }
    public String systemName() { return "知华科技应付账款自动化系统"; }
    public String scene() { return "供应商发票、验真查重、采购订单、收货、三单匹配、异常、审批与付款计划"; }
    public String initialStatus() { return "草稿"; }
    public String partyLabel() { return "供应商/发票"; }
    public String amountLabel() { return "应付金额"; }
    public String quantityLabel() { return "发票数量"; }
    public String dueLabel() { return "付款到期日"; }
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("INVOICE_INBOX", "发票收件箱", "登记电子与纸质发票并提取结构化信息"),
            new ModuleDefinition("VALIDATION", "验真查重", "校验号码、税额、供应商和重复发票"),
            new ModuleDefinition("PURCHASE_ORDER", "采购订单", "同步订单、行项目、价格和税率"),
            new ModuleDefinition("RECEIPT", "收货记录", "归集收货、退货和服务确认"),
            new ModuleDefinition("THREE_WAY_MATCH", "三单匹配", "执行订单、收货与发票金额数量匹配"),
            new ModuleDefinition("EXCEPTION", "异常处理", "分派差异、补充凭证并完成复核"),
            new ModuleDefinition("APPROVAL", "付款审批", "按金额、组织和风险执行审批"),
            new ModuleDefinition("PAYMENT_PLAN", "付款计划", "安排到期、折扣和现金流优先级"),
            new ModuleDefinition("RECONCILIATION", "对账归档", "记录付款回执并完成供应商对账")
        ); }
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    public record ModuleDefinition(String code,String name,String description) {}
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}
