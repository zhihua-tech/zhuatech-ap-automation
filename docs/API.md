# 应付账款自动化系统 API

所有业务接口默认位于 `/api`，除 `/public/**` 和健康检查外均需要 HTTP Basic 身份认证。生产环境应接入企业 IAM 或统一身份平台。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/public/about` | 产品、公司、官网和许可元数据 |
| GET | `/catalog` | 业务模块、字段标签和状态动作 |
| GET | `/dashboard` | 业务规模、金额、状态和模块统计 |
| GET/POST | `/records` | 业务台账查询与创建 |
| GET/PUT/DELETE | `/records/{id}` | 详情、草稿修改与删除 |
| POST | `/records/{id}/actions` | 执行服务端状态迁移 |
| POST | `/records/{id}/comments` | 增加协作记录 |
| GET | `/records/{id}/timeline` | 查询完整操作时间线 |
| GET | `/records/search` | 组合检索、分页和逾期筛选 |
| GET | `/records/export.csv` | 导出 UTF-8 CSV |
| GET | `/sla-summary` | SLA、逾期、风险和人员工作量 |
| POST | `/domain/decision` | 执行应付账款自动化系统专属领域规则 |
| GET/POST | `/enterprise/controls` | 企业控制项查询与幂等创建 |
| POST | `/enterprise/controls/{id}/submit` | 提交复核 |
| POST | `/admin/enterprise/controls/{id}/review` | 管理员审批或驳回 |
| POST | `/enterprise/controls/{id}/documents` | 登记附件哈希及存储元数据 |
| POST | `/enterprise/controls/{id}/complete` | 凭证完整后办结 |
| POST | `/admin/enterprise/controls/{id}/sync` | 登记外部系统回执 |

## 领域决策字段

| 字段 | 类型 | 含义 |
| --- | --- | --- |
| `invoiceNo` | String | 发票号码 |
| `purchaseOrderAmount` | double | 订单金额 |
| `receiptAmount` | double | 收货金额 |
| `invoiceAmount` | double | 发票金额 |
| `vendorActive` | boolean | 供应商有效 |
| `duplicateInvoice` | boolean | 检测到重复发票 |
| `taxValidated` | boolean | 税务校验通过 |

接口统一返回 `ApiResponse`；业务冲突使用 HTTP 409，参数错误使用 400，未认证使用 401，无权限使用 403。

## V2.0 应付自动化接口

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/api/ap/dashboard` | 订单、收货、发票、异常与付款总览 |
| POST | `/api/ap/purchase-order-lines` | 建立采购订单行 |
| POST | `/api/ap/purchase-order-lines/{id}/receipts` | 登记收货数量与金额 |
| POST | `/api/ap/invoices` | 录入供应商发票及行项目 |
| POST | `/api/ap/invoices/{id}/match` | 执行订单、收货、发票三单匹配 |
| POST | `/api/admin/ap/invoices/{id}/approve` | 审批匹配通过的发票 |
| POST | `/api/admin/ap/invoices/{id}/payment-plans` | 生成付款计划 |
| POST | `/api/admin/ap/payment-plans/{id}/pay` | 登记付款流水 |

匹配按组织、供应商和采购订单行逐项核对，超出容差会生成异常并阻止审批；只有已审批发票可以进入付款计划。
