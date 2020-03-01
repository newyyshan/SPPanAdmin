package net.sppan.base.service;

import net.sppan.base.entity.Oplog;
import net.sppan.base.service.support.IBaseService;
import net.sppan.base.vo.ZtreeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * <p>
 * 运维日志类
 * </p>
 *
 * @author SPPan
 * @since 2020-02-29
 */
public interface IOplogService extends IBaseService<Oplog, Integer> {

//	/**
//	 * 获取角色的权限树
//	 * @param roleId
//	 * @return
//	 */
//	List<ZtreeView> tree(int roleId);

	/**
	 * 修改或者新增日志
	 * @param oplog
	 */
	void saveOrUpdate(Oplog oplog);

	/**
	 * 关键字分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<Oplog> findAllByLike(String searchText, PageRequest pageRequest);

}
