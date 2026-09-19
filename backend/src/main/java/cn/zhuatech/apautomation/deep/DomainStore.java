/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apautomation.deep;import jakarta.persistence.*;import java.math.*;import java.time.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="apa_po_lines",uniqueConstraints=@UniqueConstraint(columnNames={"organizationCode","poNo","lineNo"})) class PurchaseOrderLine{@Id @GeneratedValue(strategy=GenerationType.IDENTITY)Long id;@Version long version;String organizationCode,poNo,vendorCode,itemCode;int lineNo;BigDecimal orderedQty,receivedQty=BigDecimal.ZERO,unitPrice;String status="OPEN";/**
                                                                                                                                                                                                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                                   */
protected PurchaseOrderLine(){}/**
                                                                                                                                                                                                                                                                                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                                                                  */
PurchaseOrderLine(String o,String p,int l,String v,String item,BigDecimal qty,BigDecimal price){organizationCode=o;poNo=p;lineNo=l;vendorCode=v;itemCode=item;orderedQty=qty;unitPrice=price;}}
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="apa_receipts",uniqueConstraints=@UniqueConstraint(columnNames="receiptNo")) class GoodsReceipt{@Id @GeneratedValue(strategy=GenerationType.IDENTITY)Long id;Long poLineId;String receiptNo;BigDecimal quantity;LocalDate receiptDate;String receiver;/**
                                                                                                                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                           */
protected GoodsReceipt(){}/**
                                                                                                                                                                                                                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                     */
GoodsReceipt(Long line,String no,BigDecimal qty,LocalDate date,String receiver){poLineId=line;receiptNo=no;quantity=qty;receiptDate=date;this.receiver=receiver;}}
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="apa_invoices",uniqueConstraints=@UniqueConstraint(columnNames={"organizationCode","vendorCode","invoiceNo"})) class SupplierInvoice{@Id @GeneratedValue(strategy=GenerationType.IDENTITY)Long id;@Version long version;String organizationCode,vendorCode,invoiceNo,currency;BigDecimal totalAmount,taxAmount;LocalDate invoiceDate,dueDate;String status="RECEIVED";LocalDateTime createdAt;/**
                                                                                                                                                                                                                                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                                                                   */
protected SupplierInvoice(){}/**
                                                                                                                                                                                                                                                                                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                                                                                                */
SupplierInvoice(String o,String v,String no,String c,BigDecimal total,BigDecimal tax,LocalDate date,LocalDate due){organizationCode=o;vendorCode=v;invoiceNo=no;currency=c;totalAmount=total;taxAmount=tax;invoiceDate=date;dueDate=due;createdAt=LocalDateTime.now();}}
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="apa_invoice_lines") class SupplierInvoiceLine{@Id @GeneratedValue(strategy=GenerationType.IDENTITY)Long id;Long invoiceId;String poNo,itemCode;int poLineNo,invoiceLineNo;BigDecimal quantity,unitPrice,lineAmount;/**
                                                                                                                                                                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                         */
protected SupplierInvoiceLine(){}/**
                                                                                                                                                                                                                                                                          * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                          */
SupplierInvoiceLine(Long invoice,int line,String po,int poLine,String item,BigDecimal qty,BigDecimal price){invoiceId=invoice;invoiceLineNo=line;poNo=po;poLineNo=poLine;itemCode=item;quantity=qty;unitPrice=price;lineAmount=qty.multiply(price);}}
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="apa_match_exceptions") class MatchExceptionRecord{@Id @GeneratedValue(strategy=GenerationType.IDENTITY)Long id;Long invoiceId,invoiceLineId;String exceptionType,description,status="OPEN";BigDecimal variance;LocalDateTime createdAt=LocalDateTime.now();/**
                                                                                                                                                                                                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                 */
protected MatchExceptionRecord(){}/**
                                                                                                                                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                   */
MatchExceptionRecord(Long inv,Long line,String type,String desc,BigDecimal variance){invoiceId=inv;invoiceLineId=line;exceptionType=type;description=desc;this.variance=variance;}}
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="apa_payment_plans",uniqueConstraints=@UniqueConstraint(columnNames="invoiceId")) class PaymentPlan{@Id @GeneratedValue(strategy=GenerationType.IDENTITY)Long id;Long invoiceId;LocalDate paymentDate;BigDecimal amount;String bankAccountMask,status="SCHEDULED",paymentReference;/**
                                                                                                                                                                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                        */
protected PaymentPlan(){}/**
                                                                                                                                                                                                                                                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                 */
PaymentPlan(Long invoice,LocalDate date,BigDecimal amount,String mask){invoiceId=invoice;paymentDate=date;this.amount=amount;bankAccountMask=mask;}}
