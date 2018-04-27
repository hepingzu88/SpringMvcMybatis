package yyd.yun.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import yyd.yun.aspect.CommentAnno;
import yyd.yun.beans.Operator;
import yyd.yun.beans.SceneVo;
import yyd.yun.beans.Semantic;
import yyd.yun.beans.SemanticInfo;
import yyd.yun.beans.SemanticVo;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.SceneVoDao;
import yyd.yun.dao.SemanticDao;
import yyd.yun.dao.SemanticInfoDao;
import yyd.yun.service.SemanticService;

@Service
public class SemanticServiceImpl implements SemanticService{

	@Autowired
	private SemanticDao dao;
	
	@Autowired 
	private SemanticInfoDao infoDao;
	
	@Autowired
	private SceneVoDao sceneAndIntentVoDao;
	
	private static Logger logger = LoggerFactory.getLogger(SemanticService.class);
	
	@CommentAnno(operationType="新增语料操作:",operationName="新增语料")
	@Override
	public int add(Semantic info) {
		dao.add(info);
		if(info.getSimilarityId() == null || "".equals(info.getSimilarityId())){
			dao.updateSemantic(info);
		}
		return info.getId();
	}
	
	@Override
	public int batchAdd(Semantic info){
		return dao.add(info);
	}
	
	@CommentAnno(operationType="修改语料操作:",operationName="修改语料")
	@Override
	public int updateSemantic(String data,Integer id) {
		Semantic info = new Semantic();
		Map<String,Object> map = new HashMap<>();
		List<SemanticVo>  list = new ArrayList<>();
		if(!"".equals(data)){
			String arr[] = data.split(",");
			String value = "";
			String key = "";
			info.setText(arr[1]);
			map.put("intent", arr[0]);
			SemanticVo vo = null; 
			for (int i = 2;i<arr.length;i=i+4) {
				vo = new SemanticVo();
				vo.setStart(arr[i]);
				vo.setEnd(arr[i+1]);
				vo.setEntity(arr[i+2]);
				vo.setValue(arr[i+3]);
				list.add(vo);
				info.setList(list);
		    }
		}
		map.put("text", info.getText());
		map.put("entities", list);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		String result = jsonObject.toString();
		
		info.setId(id); 
		info.setSemantic(result);
		
		return dao.updateLabelSemantic(info);
	}

	/*@Override
	public PageInfo<SemanticInfo> findSemantic(Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<SemanticInfo> list = dao.findSemantic();
        if (list != null && list.size() > 0) {
        	List<SemanticInfo> semantic = new ArrayList<>();
    		SemanticInfo s = null;
    		for (SemanticInfo semanticInfo : list) {
    			s = new SemanticInfo();
    			if(!"".equals(semanticInfo.getSemantic()) && semanticInfo.getSemantic() != null){
    				Map<String, Object> map  =  parseJSON2Map(semanticInfo.getSemantic());
    				List<Object> li = (List)map.get("entities");
    				JSONArray jsonArray = JSONArray.fromObject(li);
    			    JSONArray json = JSONArray.fromObject(jsonArray.toString());
    		        List<SemanticVo> listVo = (List<SemanticVo>) jsonArray.toCollection(jsonArray, SemanticVo.class);
    				String text = (String) map.get("text");
    				String intent = (String) map.get("intent");
    				s.setSemantic(semanticInfo.getSemantic());
    				s.setId(semanticInfo.getId());
    				s.setText(text);
    				s.setIntent(intent);
    				s.setList(listVo);
    				semantic.add(s);
    			}else{
    				semantic.add(semanticInfo);
    			}
    		}
            return new PageInfo<SemanticInfo>(semantic);
        } else {
            return null;
        }
	}*/

	//删除同义语句
	@CommentAnno(operationType="删除同义句操作:",operationName="删除同义句")
	@Override
	public int deletSemantic(Integer operatorId,Integer id) {
		return dao.deletSemantic(operatorId,id);
	}
	
	//删除语料以及同义语句
	@CommentAnno(operationType="删除语料以及同义句操作:",operationName="删除语料以及同义句")
	@Override
	public int deleteSemanticAndSimilarity(Integer operatorId,Integer id){
		return dao.deleteSemanticAndSimilarity(operatorId,id);
	}

	public static Map<String, Object> parseJSON2Map(String jsonStr){    
        Map<String, Object> map = new HashMap<String, Object>();    
        JSONObject json = JSONObject.fromObject(jsonStr);    
        for(Object k : json.keySet()){    
            Object v = json.get(k);     
            if(v instanceof JSONArray){    
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();    
                Iterator<JSONObject> it = ((JSONArray)v).iterator();    
                while(it.hasNext()){    
                    JSONObject json2 = it.next();    
                    list.add(parseJSON2Map(json2.toString()));    
                }    
                map.put(k.toString(), list);    
            } else {    
                map.put(k.toString(), v);    
            }    
        }    
        return map;    
    }

	@Override
	public PageInfo<SemanticInfo> find(Integer pageNum,Semantic info) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<SemanticInfo> list = infoDao.findSemantic(info);
		logger.info(" semantic   -----   " + list);
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<SemanticInfo>(list);
         } else {
            return null;
         }
	}
	
	//根据ID查询。并解析
	@Override
	public Semantic findById(Integer id,Integer userId) {
		Semantic info = infoDao.findById(id,userId);
		if(info == null){
			return null;
		}
		Semantic s = null;
		if(!"".equals(info.getSemantic()) && info.getSemantic() != null){
			Map<String, Object> map  =  parseJSON2Map(info.getSemantic());
			List<Object> li = (List)map.get("entities");
			JSONArray jsonArray = JSONArray.fromObject(li);
		    JSONArray json = JSONArray.fromObject(jsonArray.toString());
	        List<SemanticVo> listVo = (List<SemanticVo>) jsonArray.toCollection(jsonArray, SemanticVo.class);
	        info.setList(listVo);
		}
		return info;
	}
	
	@Override
	public Semantic selectSemantic(Integer id,Integer userId){
		return infoDao.selectSemantic(id,userId);
	}
	

	@Override
	public List<Semantic> findIntent() {
		return dao.findIntent();
	}

	@Override
	public PageInfo<Semantic> findSemantic(Integer pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<Semantic> findByIdAndUserId(Integer id, Integer operatorId,Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<Semantic> list = dao.findByIdAndUserId(id, operatorId);
		 if (list != null && list.size() > 0) { 
			 for (Semantic semantic : list) {
				if(semantic.getId().equals(id)){
					list.remove(semantic);
					break;
				}
			}
			 return new PageInfo<Semantic>(list);
         } else {
            return null;
         }
	}

	@CommentAnno(operationType="修改语料以及同义句操作:",operationName="修改语料以及同义句")
	@Override
	public int updateSemanticAnswer(String arr, Integer id,Integer operatorId) {
		Semantic st = dao.querysimilarityId(id, operatorId);
		SceneVo vo = new SceneVo();
		vo.setId(st.getSceneId());
		vo.setIntentId(st.getIntentId());
		vo.setOperatorId(operatorId);
		List<SceneVo> listVo = sceneAndIntentVoDao.findSceneAndIntent(vo);
		String intentEnglish = listVo.get(0).getIntentEnglish();
		String sceneEnglish = listVo.get(0).getSceneEnglish();
		List<Object> lsit = new ArrayList<>();
		
		String a[] = arr.split(",");
		Semantic semantic = new Semantic();
		List<Semantic> list = new ArrayList<>();
		for(int i =0; i < a.length; i=i+3){
			semantic = new Semantic();
			semantic.setId(Integer.valueOf(a[i]));
			if(a[i+1].equals("null")){
				semantic.setText(null);
			}else{
				semantic.setText(a[i+1]);
			}
			if(a[i+2].equals("null")){
				semantic.setAnswer(null);
			}else{
				semantic.setAnswer(a[i+2]);
			}
			semantic.setOperatorId(operatorId);
			semantic.setSimilarityId(st.getSimilarityId());
			semantic.setSceneId(st.getSceneId());
			semantic.setIntentId(st.getIntentId());
			semantic.setApplyAge(st.getApplyAge());
			semantic.setSentiment(st.getSentiment());
			list.add(semantic);
		}
		int index = 0;
		for (Semantic semantic2 : list) {
			if(semantic2.getId() > 0){
				index = dao.updateSemantic(semantic2);
			}else{
				Map<String, Object> map = new HashMap<>();
				//map.put("service_intent", sceneEnglish+":"+intentEnglish);//update
				map.put("service", sceneEnglish);//update new
				map.put("intent",intentEnglish);//update new
				map.put("text", semantic2.getText());
				map.put("entities",lsit);
				JSONObject semantisJson = JSONObject.fromObject(map);
				semantic2.setSemantic(semantisJson.toString());
				index = dao.add(semantic2);
			}
		}
		return index;
	}  
	
	public static void main(String[] args) {
		/*Map<String, Object> map = new HashMap<>();
		List<Object> lsit = new ArrayList<>();
		map.put("service_intent", "qianqi"+"_"+"query");
		map.put("text", "哈哈哈");
		map.put("entities",lsit);
		String semantisJson = JSONObject.fromObject(map).toString();
		System.out.println(semantisJson);*/
		String s = "{'service':'music','intent':'respone','entities':[{'start':'5','end':'16','value':'bye','entity':'songName'}],'text':'帮我放一首bye bye bye'}";
		Map<String, Object> map = SemanticServiceImpl.parseJSON2Map(s);
		System.out.println(map.get("service"));
	}
	
	
	//修改场景  行为  情感状态  年龄段 同时修改同义句
	@CommentAnno(operationType="修改语料操作:",operationName="修改语料")
	@Override
	public int updateSemanticInfo(Semantic semantic){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		List<Semantic> list = dao.queryAllsimilarityId(semantic.getId(),admin.getId());//所有同义句
		Map<String, Object> map  =  parseJSON2Map(semantic.getSemantic());//获取原语料意图;
		//String serviceIntent = (String) map.get("service_intent");
		String service = (String) map.get("service");
		String intent = (String) map.get("intent");
		List<Object> entities = new ArrayList<>();
		int index = -1;
		for (Semantic semantic2 : list) {
			if(semantic2.getSemantic() == null || "".equals(semantic2.getSemantic())){
				String semanticJson = updateJson(semantic2.getText(),service,intent,entities);
				semantic2.setApplyAge(semantic.getApplyAge());
				semantic2.setSceneId(semantic.getSceneId());
				semantic2.setIntentId(semantic.getIntentId());
				semantic2.setSentiment(semantic.getSentiment());
				semantic2.setSemantic(semanticJson);
				index = dao.updateSemanticJson(semantic2);
			}else{
				Map<String, Object> map2  =  parseJSON2Map(semantic2.getSemantic());//获取原语料意图;
				List<Object> lsit = (List<Object>) map2.get("entities");
				String semanticJson2 = updateJson(semantic2.getText(),service,intent,lsit);
				semantic2.setApplyAge(semantic.getApplyAge());
				semantic2.setSceneId(semantic.getSceneId());
				semantic2.setIntentId(semantic.getIntentId());
				semantic2.setSentiment(semantic.getSentiment());
				semantic2.setSemantic(semanticJson2);
				index = dao.updateSemanticJson(semantic2);
			}
		}
		return index;
	}
	
	public static String updateJson(String text,String service,String intent,List<Object> list){
		Map<String, Object> map = new HashMap<>();
		//map.put("service_intent",serviceIntent);
		map.put("service",service);
		map.put("intent",intent);
		map.put("text",text);
		map.put("entities",list);
		return JSONObject.fromObject(map).toString();
	}
	
	
	
	@Override
	public List<Semantic> seletSemantic(Semantic semantic) {
		return dao.seletSemantic(semantic);
	}

	@CommentAnno(operationType="修改语料操作:",operationName="修改语料")
	@Override
	public int updateLabelSemantic(Semantic semantic) {
		return dao.updateLabelSemantic(semantic);
	}

	@CommentAnno(operationType="批量删除语料操作:",operationName="批量删除语料")
	@Override
	public int batchDelete(String[] arrys) {
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		return dao.batchDelete(arrys,admin.getId());
	}

	@CommentAnno(operationType="修改语料操作:",operationName="修改语料")
	@Override
	public int updateSemanticJson(Semantic semantic) {
		return dao.updateSemanticJson(semantic);
	}

	//导出
	@Override
	public List<SemanticInfo> exportSemantic(SemanticInfo info) {
		List<SemanticInfo> list = infoDao.exportSemantic(info);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	//新增同义句
	@CommentAnno(operationType="新增同义句操作:",operationName="新增同义句")
	@Override
	public int insertSynonymous(Semantic semantic) {
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Semantic sc = dao.querysimilarityId(semantic.getId(), admin.getId());
		semantic.setSceneId(sc.getSceneId());
		semantic.setIntentId(sc.getIntentId());
		semantic.setApplyAge(sc.getApplyAge());
		semantic.setSimilarityId(sc.getId());
		semantic.setSentiment(sc.getSentiment());
		semantic.setOperatorId(sc.getOperatorId());
		Map<String, Object> map = SemanticServiceImpl.parseJSON2Map(sc.getSemantic());
		//String serviceIntent = (String) map.get("service_intent");
		String service = (String) map.get("service");
		String intent = (String) map.get("intent");
		String json = updateJson(semantic.getText(),service,intent,new ArrayList<>());
		semantic.setSemantic(json);
		return dao.add(semantic);
	}

	@CommentAnno(operationType="批量删除同义句语料操作:",operationName="批量删除同义句语料")
	@Override
	public int batchDeleteSynonymous(String[] arrys) {
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		return dao.batchDeleteSynonymous(arrys, admin.getId());
	}
}
