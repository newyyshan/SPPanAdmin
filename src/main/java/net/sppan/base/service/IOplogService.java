package net.sppan.base.service;

import net.sppan.base.entity.Oplog;
import net.sppan.base.service.support.IBaseService;
import net.sppan.base.vo.OpLogVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * <p>
 * 运维日志类
 * </p>
 *
 * @author SPPan
 * @since 2020-02-29
 */
public interface IOplogService extends IBaseService<Oplog, Integer> {

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

	/**
	 * 条件查询
	 * @param opLogVO - 查询参数
	 * @param pageRequest - 分页参数
	 * @return 查询结果
	 */
	Page<Oplog> findByConditions(OpLogVO opLogVO, PageRequest pageRequest);
}
