package net.sppan.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import net.sppan.base.entity.support.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 * 运维日志表
 * </p>
 *
 * @author SPPan
 * @since 2020-02-29
 */
@Entity
@Table(name = "tb_oplog")
public class Oplog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	/**
	 * 日志状态 0 新建 1 跟进 2 办结
	 */
	private Integer state;

	/**
	 * 区域
	 */
	private String region;

	/**
	 * 广场
	 */
	private String square;

	/**
	 * 事项名称
	 */
	private String eventname;

	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 更新时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 更新人
	 */
	private String updateUser;

	/**
	 * 备注
	 */
	private String remark;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "parent_id")
//	private Oplog parent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public String getSquare() {
		return square;
	}

	public void setSquare(String square) {
		this.square = square;
	}


	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
