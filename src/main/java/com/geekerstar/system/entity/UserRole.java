package com.geekerstar.system.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* 用户角色关联表UserRole
*
* @author Geekerstar
* @since 2020-01-31
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("sys_user_role")
    @ApiModel(value="UserRole对象", description="用户角色关联表")
    public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "用户id")
    private Long userId;

            @ApiModelProperty(value = "角色id")
    private Long roleId;


}
