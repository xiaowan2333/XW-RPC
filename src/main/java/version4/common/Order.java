package version4.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName order
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 关联user表的openId
     */
    private String openid;

    /**
     * 项目名称
     */
    private String proname;

    /**
     * 预约时间
     */
    private String makedate;

    /**
     * 预约客户姓名
     */
    private String username;

    /**
     * 预约状态
     */
    private String orderstate;

    /**
     * 下单时间
     */
    private String placedate;

    /**
     * 手机号
     */
    private String usertell;

    /**
     * 留言
     */
    private String information;

    /**
     * 关联商家id
     */
    private Long busid;

    /**
     * 关联产品id
     */
    private Long proid;

    /**
     * 
     */
    private Long tecid;

}