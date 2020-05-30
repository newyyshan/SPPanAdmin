package net.sppan.base.controller.admin.system;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Oplog;
import net.sppan.base.service.IOplogService;
import net.sppan.base.vo.OpLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/oplog")
public class OplogController extends BaseController {
	@Autowired
	private IOplogService oplogService;

	@RequestMapping("/index")
	public String index() {
		return "admin/oplog/index";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Page<Oplog> list(
		@RequestParam(value = "searchText", required = false) String searchText
	) {
		Page<Oplog> page = oplogService.findAllByLike(searchText, getPageRequest());
		return page;
	}

	@RequestMapping("/list2")
	@ResponseBody
	public Page<Oplog> list2(OpLogVO opLogVO) {
		 Integer state = opLogVO.getState();
		 String region = opLogVO.getRegion();
		 String square = opLogVO.getSquare();
		 String createUser = opLogVO.getCreateUser();
		 String updateUser = opLogVO.getUpdateUser();
		 String eventname = opLogVO.getEventname();
		 String[] createTime = opLogVO.getCreateTime();
		 String[] updateTime = opLogVO.getUpdateTime();

		System.out.println(StringUtils.join(opLogVO.getCreateTime(), ","));
		System.out.println(StringUtils.join(opLogVO.getUpdateTime(), ","));
		System.out.println(opLogVO.toString());
		Page<Oplog> page = oplogService.findAllByLike(opLogVO.getEventname(), getPageRequest());
		return page;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		List<Oplog> list = oplogService.findAll();
		map.put("list", list);
		return "admin/oplog/form";
	}


	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, ModelMap map) {
		Oplog oplog = oplogService.find(id);
		map.put("oplog", oplog);

		List<Oplog> list = oplogService.findAll();
		map.put("list", list);
		return "admin/oplog/form";
	}

	@RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Oplog oplog, ModelMap map) {
		try {
			oplogService.saveOrUpdate(oplog);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id, ModelMap map) {
		try {
			oplogService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
}
