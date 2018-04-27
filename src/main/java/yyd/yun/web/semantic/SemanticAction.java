package yyd.yun.web.semantic;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Affective;
import yyd.yun.beans.AgeBand;
import yyd.yun.beans.Intent;
import yyd.yun.beans.Operator;
import yyd.yun.beans.Scene;
import yyd.yun.beans.SceneVo;
import yyd.yun.beans.Semantic;
import yyd.yun.beans.SemanticInfo;
import yyd.yun.common.ResultModel;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.SemanticDao;
import yyd.yun.service.AffectiveService;
import yyd.yun.service.AgeBandService;
import yyd.yun.service.ApplyService;
import yyd.yun.service.EntityService;
import yyd.yun.service.IntentService;
import yyd.yun.service.SceneService;
import yyd.yun.service.SemanticService;
import yyd.yun.service.impl.SemanticServiceImpl;
import yyd.yun.utils.PoiUtil;


@RequestMapping("/semantic")
@Controller
public class SemanticAction {

	@Autowired
	private SemanticService semanticService;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private SceneService sceneService;
	
	@Autowired
	private EntityService entityService;
	
	@Autowired
	private IntentService intentService;
	
	@Autowired
	private AgeBandService ageBandService;
	
	@Autowired 
	private AffectiveService affectiveSrevice;
	
	@Autowired 
	private SemanticDao dao;
	
	
	//公开场景
	@RequestMapping(value="/semanticIndexPublic",method=RequestMethod.GET)
	public String indexPublic(ModelMap map,Integer id){
		List<Scene> scene = sceneService.findById(id);
		map.put("list", scene);
		return "semantic/semantic-index-public";
	}
	
	@RequestMapping(value="/findPublic")
	@ResponseBody
	public Map<String,Object> findPublic(Integer page,Integer limit,ModelMap modelMap,Integer sceneId,Semantic info){
		info.setOperatorId(1);
		if(page==null||page<1){
			page=1;
        }
		Map<String,Object> map = new HashMap<>();
		PageInfo<SemanticInfo> pages = semanticService.find(page,info);
		if(pages == null){
			map = null;
			return map;
		}
		map.put("code", 0);
		map.put("msg","");
		map.put("count",pages.getTotal());
		map.put("data", pages.getList());
		return map;
	}
	
	
	@RequestMapping(value="/semanticIndex",method=RequestMethod.GET)
	public String index(ModelMap map,Integer id,Integer page){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		List<Scene> scene = sceneService.findById(admin.getId());
		map.put("list", scene);
		map.put("page", page);
		return "semantic/semantic-index";
	}
	
	
	@RequestMapping(value="/findIntent",method=RequestMethod.POST)
	@ResponseBody
	public List<Scene> findIntent(){
		List<Scene> info = sceneService.findScene();
		return info;
	}
	
	
	@RequestMapping(value="/find")
	@ResponseBody
	public Map<String,Object> find(Integer page,Integer limit,ModelMap modelMap,Integer sceneId,Semantic info){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		info.setOperatorId(admin.getId());
		if(page==null||page<1){
			page=1;
        }
		Map<String,Object> map = new HashMap<>();
		PageInfo<SemanticInfo> pages = semanticService.find(page,info);
		if(pages == null){
			map = null;
			return map;
		}
		map.put("code", 0);
		map.put("msg","");
		map.put("count",pages.getTotal());
		map.put("data", pages.getList());
		return map;
	}
	
	/**
	 * 新增同义句
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/insertSynonymous",method=RequestMethod.GET)
	public String insertSynonymous(Integer id,ModelMap map){
		map.put("id",id);
		return "semantic/semantic-insertSynonymous";
	}
	
	@RequestMapping(value="/insertSynonymous",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel insertSynonymousPost(Semantic semantic){
		if(semanticService.insertSynonymous(semantic) >0 ){
			return new ResultModel("新增成功",true);
		}
		return new ResultModel("新增失败",false);
	}
	
	@RequestMapping(value="/batchDeleteSynonymous",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel batchDeleteSynonymous(String arrys[]){
		if(semanticService.batchDeleteSynonymous(arrys) > 0 ){
			return new ResultModel("删除成功",true);
		}
		return new ResultModel("删除失败",false);
	}
	
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Integer id,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Semantic info = semanticService.findById(id,admin.getId());
		List<Scene> list = sceneService.findById(admin.getId());
		
		//查询语料使用的场景下的实体
		SceneVo vo = new SceneVo();
		vo.setId(info.getSceneId());
		vo.setOperatorId(info.getOperatorId());
		List<SceneVo> entity = entityService.findSceneAndEntity(vo);

		map.put("info", info);
		map.put("slist", list);
		map.put("entity", entity);
		
		return "semantic/semantic-edit";
	}
	
	//批量删除
	@RequestMapping(value="/batchDelete",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel batchDelete(String[] arrys){
		if(arrys == null ||(arrys != null && arrys.length==0)){
			return new ResultModel("请勾选复选框",false);
		}
		if(semanticService.batchDelete(arrys) >0){
			 return new ResultModel("删除成功",true);
		}
		 return new ResultModel("删除失败",false);
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel delete(Integer id){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(id == null){
			return new ResultModel("删除失败",false);
		}
		if(semanticService.deletSemantic(admin.getId(),id) > 0){
			return new ResultModel("删除成功",true);
		}
		return new ResultModel("删除失败",false);
	}
	
	
	@RequestMapping(value="/deleteSemanticAndSimilarity",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteSemanticAndSimilarity(Integer id){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(id == null){
			return new ResultModel("删除失败",false);
		}
		if(semanticService.deleteSemanticAndSimilarity(admin.getId(),id) > 0){
			return new ResultModel("删除成功",true);
		}
		return new ResultModel("删除失败",false);
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		List<Scene> list = sceneService.findById(admin.getId());
		List<Intent> intent = intentService.queryByIdIntent(admin.getId());
		Affective aff = new Affective();
		aff.setOperatorId(admin.getId());
		List<Affective> affList = affectiveSrevice.findAffectiveList(aff);
		map.put("action",intent);
		map.put("list", list);
		map.put("affList", affList);
		return "semantic/semantic-add";
	}
	
	
	@RequestMapping(value="/addText",method=RequestMethod.POST)
	@ResponseBody
	public String addSemanticText(Semantic info){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		info.setOperatorId(admin.getId());
		if(semanticService.add(info) > 0){
			return "true";
		}
		return "fasle";
	}
	
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String semanticUpdate(Semantic semantic){
		if(semanticService.updateLabelSemantic(semantic) > 0){
			return "true";
		}
		return "fasle";
	}
	
	//修改场景     行为   情感状态  适用年龄段
	@RequestMapping(value="/updateScene",method=RequestMethod.GET)
	public String updateScene(Integer id,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();//当前用户
		
		Semantic info = semanticService.findById(id,admin.getId());//查询用户当前点击编辑的语料（如果有标注则解析）
		map.put("info", info);
		List<Scene> scene = sceneService.findById(admin.getId());//查询用户自定义的所有场景
		if(scene.isEmpty()){
			map.put("listScene", null);
		}
		map.put("listScene", scene);
		
		Semantic semantic = semanticService.selectSemantic(id, admin.getId());//查询用户当前点击编辑的语料
		map.put("semantic", semantic);
		
		//查询语料使用的场景下的实体
		SceneVo vo = new SceneVo();
		vo.setId(semantic.getSceneId());
		vo.setOperatorId(semantic.getOperatorId());
		List<SceneVo> entity = entityService.findSceneAndEntity(vo);
		if(entity.isEmpty()){
			map.put("entity", null);
		}
		map.put("entity", entity);
		
		Affective aff = new Affective();
		aff.setOperatorId(admin.getId());
		List<Affective> affList = affectiveSrevice.findAffectiveList(aff);//查询当前用户所有情感状态
		if(affList.isEmpty()){
			map.put("affList", null);
		}
		map.put("affList", affList);
		
		List<AgeBand> ageBandList = ageBandService.queryByIdAgeBand(admin.getId());//查询当前用户所有自定义的适用年龄段
		if(ageBandList.isEmpty()){
			map.put("ageBandList", null);
		}
		map.put("ageBandList", ageBandList);
		
		vo = new SceneVo();
		vo.setOperatorId(admin.getId());
		vo.setId(semantic.getSceneId());
		List<SceneVo> intentList = intentService.findSceneAndIntent(vo);
		if(intentList.isEmpty()){
			map.put("intentList", null);
		}
		map.put("intentList", intentList);
		return "semantic/updateScene";
	}
	
	
	//修改场景     行为
	@RequestMapping(value="/updateScene",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel updateScenePost(Semantic semantic,ModelMap map){
		if(semanticService.updateSemanticJson(semantic) > 0){
			if(semanticService.updateSemanticInfo(semantic) > 0){
				return new ResultModel("修改成功",true);
			}
		} 
		return new ResultModel("修改失败",false);
	}
	
	
	//Scene 增加场景
	@RequestMapping(value="/addScene",method=RequestMethod.GET)
	public String sceneShow(){
		return "semantic/semantic-scene";
	}
	
	
	@RequestMapping(value="/addScene",method=RequestMethod.POST)
	@ResponseBody
	public String sceneAdd(Scene scene){
		int index = sceneService.addScene(scene);
		if(index > 0){
			return "true";
		}
		return "false";
	}
	
	
	@RequestMapping(value="/findEntity",method=RequestMethod.POST)
	@ResponseBody
	public List<SceneVo> findScene(ModelMap map,Integer sceneId){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		SceneVo vo = new SceneVo();
		vo.setId(sceneId);
		vo.setOperatorId(admin.getId());
		List<SceneVo> entityList = entityService.findSceneAndEntity(vo);
		if(entityList.isEmpty()){
			return null;
		}
		return entityList;
	}
	
	//上传
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	public String uploadShow(ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Scene sc = new Scene();
		sc.setOperatorId(admin.getId());
		List<Scene> list =  sceneService.findSceneById(sc);
		map.put("list", list);
		return "semantic/semantic-upload";
	}
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String semanticDisplay(ModelMap modelMap){
		return "semantic/semantic";
	}
	
	@RequestMapping(value="/query",method=RequestMethod.POST)
	public String findSemantic(ModelMap modelMap,Integer pageNum){
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<Semantic> page = semanticService.findSemantic(pageNum);
		modelMap.put("page", page);
		return "semantic/semantic-list";
	}
	
	
	//导出
	@RequestMapping(value="/export",method=RequestMethod.GET)
	public void export(SemanticInfo info,HttpServletResponse response){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		info.setOperatorId(admin.getId());
		List<SemanticInfo> list = semanticService.exportSemantic(info);
		PoiUtil.getInstance().exportExcel(list,AdminConstant.EXPORT_PATH,response);//
	}
	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel upload(@RequestParam MultipartFile[] file,Scene scene,HttpSession session){
		if(scene.getId() == null || "".equals(scene.getId())){
			return new ResultModel("请选择场景",false);
		}
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		String path = "/opt/www_80/web_semantic";
		//String path = "E:/yuliao";
		
		if(!file[0].getOriginalFilename().endsWith(".xls")&&!file[0].getOriginalFilename().endsWith(".xlsx")){
			return new ResultModel("文件不是excel类型",false);
		}
		List<Scene> sceneList = sceneService.findSceneById(scene);
        /*Progress progress = (Progress) session.getAttribute(AdminConstant.UPLOAD_PROGRESS);
        progress.setSucceed(1);*/
       /* if(progress!=null){
            progress.setContentLength(file[0].getSize());
        }*/
		try { 
			 for(MultipartFile item:file){
				String filename = item.getOriginalFilename();
				File files = new File(path,filename);
				if(!files.getParentFile().exists()){
					files.getParentFile().mkdirs();
				}
				item.transferTo(files);//存到服务器
			 }
			 for(MultipartFile item:file){
				 String filename = item.getOriginalFilename();
				 getDataFromExcel(sceneList,filename,semanticService,dao,admin);
				 //map = getDataFromExcel2(sceneId,filename,admin);
			 }
         } catch (Exception e) {  
        	// progress.setSucceed(Progress.PROGRESS_FAIL);
        	 return new ResultModel("服务器异常",false);
         }  
		 return new ResultModel("批量导入成功",true);
	}
	
	public static void main(String[] args) {
		String path = "f:/opt/www_80/web_semantic";
		String filename = "test.xsl";
		File files = new File(path,filename);
		if(!files.getParentFile().exists()){
			files.getParentFile().mkdirs();
			System.out.println("jdjkgl");
		}
		
	}
	
	public static ResultModel getDataFromExcel(List<Scene> scene,String path,SemanticService semanticService,SemanticDao dao,Operator admin){
		 	//String filePath = "E:/yuliao/"+path;
		 	String filePath = "/opt/www_80/web_semantic/"+path;
	        FileInputStream fis =null;
	        Workbook wookbook = null;
	        try{
	              fis = new FileInputStream(filePath);//获取一个绝对地址的流
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        try {
	        	if(filePath.endsWith(".xls")){
	        		 wookbook = new HSSFWorkbook(fis);//得到工作簿HSSFWorkbook
	 	        }
	        	if(filePath.endsWith(".xlsx")){
	        		wookbook = new XSSFWorkbook(fis);//得到工作簿 XSSFWorkbook
	 	        }
	        	
	        } catch (Exception ex) {
	        	return new ResultModel("服务器异常",false);
	        }
	         
	        //得到一个工作表
	        Sheet sheet = wookbook.getSheetAt(0);
	        for (int x = 0; x < wookbook.getNumberOfSheets(); x++) {
	        	sheet = wookbook.getSheetAt(x);
	        	//获得表头
		        Row rowHead = sheet.getRow(0);
		        //获得数据的总行数
		        int totalRowNum = sheet.getLastRowNum();
		        //要获得属性
		        String text = null;
		        String answer = null;
		        int key = 0;
		        
		        List<Integer> keyList = new ArrayList<>();
		        Semantic se = null;
		        List<Semantic> list = new ArrayList<>();
		        List<Object> entities = new ArrayList<>();
		       //获得所有数据
		        for(int i = 0 ; i <= totalRowNum+1; i++){
		        	se = new Semantic();
		            //获得第i行对象
		            Row row = sheet.getRow(i);
		            
		            if(row == null){
		            	for (Semantic s : list) {
			            	 key = semanticService.add(s);
			            	 keyList.add(key);
						}
		            	for (Semantic s : list) {
		            		s.setSimilarityId(keyList.get(0));
		            		dao.updateSemantic(s);
						}
		            	list = new ArrayList<>();
		            	keyList = new ArrayList<>();
		            	continue;
		            }
		            //获得获得第i行第0列的 String类型对象
		            Cell cell = row.getCell((short)0);
		            Cell cell2 = row.getCell((short)1);
	                if ((cell == null || (cell.getCellType() == cell.CELL_TYPE_BLANK)) && (cell2 == null || (cell2.getCellType() == cell2.CELL_TYPE_BLANK))){
	                	for (Semantic s : list) {
			            	 key = semanticService.add(s);
			            	 keyList.add(key);
						}
		            	for (Semantic s : list) {
		            		s.setSimilarityId(keyList.get(0));
		            		dao.updateSemantic(s);
						}
		            	list = new ArrayList<>();
		            	keyList = new ArrayList<>();
		            	continue;
	                }
		           
		            if(cell != null){
		            	if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
		            		text = String.valueOf(cell.getNumericCellValue());
		            	}else{
		            		text = cell.getStringCellValue().toString();
		            	}
		            }else{
		            	text = null;
		            }
		            
		            if(cell2 != null){
		            	if(Cell.CELL_TYPE_NUMERIC == cell2.getCellType()){
		            		answer = String.valueOf(cell2.getNumericCellValue());
		            	}else{
		            		answer = cell2.getStringCellValue().toString();
		            	}
		            }else{
		            	answer = null;
		            }
		           se.setText(text);
		           se.setSceneId(scene.get(0).getId());
		           se.setAnswer(answer);
		           se.setOperatorId(admin.getId());
		           se.setSemantic(SemanticServiceImpl.updateJson(text, scene.get(0).getSceneEnglish(),"",entities));
		           list.add(se);
		        }
			}
	        try {
	        	fis.close();
			} catch (IOException e) {
				return new ResultModel("服务器异常",false);
			}
	        return new ResultModel("导入成功",true);
	    }
	
	
	
	
	public static Map<String,Object> getDataFromExcel2(Integer sceneId,String path,Operator admin){
	 	//String filePath = "E:/yuliao/"+path;
	 	String filePath = "/opt/www_80/web_semantic/"+path;
        FileInputStream fis =null;
        Workbook wookbook = null;
        Map<String,Object> map = new HashMap<>();
        try{
              fis = new FileInputStream(filePath);//获取一个绝对地址的流
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
        	if(filePath.endsWith(".xls")){
        		 wookbook = new HSSFWorkbook(fis);//得到工作簿HSSFWorkbook
 	        }
        	if(filePath.endsWith(".xlsx")){
        		wookbook = new XSSFWorkbook(fis);//得到工作簿 XSSFWorkbook
 	        }
        } catch (Exception ex) {
        	return null;
        }
        
        //得到一个工作表
        int key = 0;
        Sheet sheet = wookbook.getSheetAt(0);
        for (int x = 0; x < wookbook.getNumberOfSheets(); x++) {
        	sheet = wookbook.getSheetAt(x);
        	//获得表头
	        Row rowHead = sheet.getRow(0);
	        //获得数据的总行数
	        int totalRowNum = sheet.getLastRowNum();
	        //要获得属性
	        String text = null;
	        String answer = null;
	        
	        List<Integer> keyList = new ArrayList<>();
	        Semantic se = null;
	        List<Semantic> list = new ArrayList<>();
	        
	       //获得所有数据
	        for(int i = 0 ; i <= totalRowNum+1; i++){
	        	se = new Semantic();
	            //获得第i行对象
	            Row row = sheet.getRow(i);
	            
	            if(row == null){
	            	map.put((key++)+"",list);
	            	list = new ArrayList<>();
	            	continue;
	            }
	            //获得获得第i行第0列的 String类型对象
	            Cell cell = row.getCell((short)0);
	            Cell cell2 = row.getCell((short)1);
                if ((cell == null || (cell.getCellType() == cell.CELL_TYPE_BLANK)) && (cell2 == null || (cell2.getCellType() == cell2.CELL_TYPE_BLANK))){
                	map.put((key++)+"",list);
	            	list = new ArrayList<>();
	            	continue;
                }
	           
	            if(cell != null){
	            	text = cell.getStringCellValue().toString();
	            }else{
	            	text = null;
	            }
	            
	            if(cell2 != null){
	            	if(Cell.CELL_TYPE_NUMERIC == cell2.getCellType()){
	            		answer = String.valueOf(cell2.getNumericCellValue());
	            	}else{
	            		answer = cell2.getStringCellValue().toString();
	            	}
	            }else{
	            	answer = null;
	            }
	           se.setText(text);
	           se.setSceneId(sceneId);
	           se.setAnswer(answer);
	           se.setOperatorId(admin.getId());
	           list.add(se);
	        }
		}
        try {
        	fis.close();
		} catch (IOException e) {
			return null;
		}
        return map;
    }
	
}
