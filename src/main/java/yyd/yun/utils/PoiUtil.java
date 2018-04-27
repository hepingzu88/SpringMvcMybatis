package yyd.yun.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import yyd.yun.beans.Semantic;
import yyd.yun.beans.SemanticInfo;
import yyd.yun.service.SemanticService;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public class PoiUtil {
	
    private static PoiUtil poiUtil = new PoiUtil();
  
    @Autowired
    private static SemanticService semanticService;
    
    private PoiUtil(){}
    
    public static PoiUtil getInstance(){
        return poiUtil;
    }

    public void excel(List<Map<String,String>> list, OutputStream os) throws IOException {
        if(list!=null && list.size()>0){
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("resources");
            HSSFRow _row = sheet.createRow(0);
            _row.createCell(0).setCellValue("filename");
            _row.createCell(1).setCellValue("Artist");
            _row.createCell(2).setCellValue("Title");
            _row.createCell(3).setCellValue("Album");
            _row.createCell(4).setCellValue("size");
            for(int i=0;i<list.size();i++){
                Map<String, String> map = list.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                if(StringUtils.isNotBlank(map.get("filename"))){
                    row.createCell(0).setCellValue(map.get("filename"));
                }
                if(StringUtils.isNotBlank(map.get("Artist"))){
                    row.createCell(1).setCellValue(map.get("Artist"));
                }
                if(StringUtils.isNotBlank(map.get("Title"))){
                    row.createCell(2).setCellValue(map.get("Title"));
                }
                if(StringUtils.isNotBlank(map.get("Album"))){
                    row.createCell(3).setCellValue(map.get("Album"));
                }
                if(StringUtils.isNotBlank(map.get("size"))){
                    row.createCell(4).setCellValue(map.get("size"));
                }
            }
            wb.write(os);
            os.close();
        }
    }
    
    public void exportExcel(List<SemanticInfo> list,String path,HttpServletResponse  response){
        if(list!=null && list.size()>0){
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("resources");
            HSSFRow _row = sheet.createRow(0);
            String head [] = {"ID","问题","回答","语料JSON","情感状态","同义句ID","适用年龄"}; 
            for (int i = 0; i < head.length; i++) {
            	 _row.createCell(i).setCellValue(head[i]);
			}
            int index = 1;
            for (SemanticInfo s : list) {
            	HSSFRow row = sheet.createRow(index++);
            	if(StringUtils.isNotBlank(String.valueOf(s.getId()))){
                    row.createCell(0).setCellValue(s.getId());
                }
            	if(StringUtils.isNotBlank(s.getText())){
                    row.createCell(1).setCellValue(s.getText());
                }
                if(StringUtils.isNotBlank(s.getAnswer())){
                    row.createCell(2).setCellValue(s.getAnswer());
                }
                if(StringUtils.isNotBlank(s.getSemantic())){
                    row.createCell(3).setCellValue(s.getSemantic());
                }
                if(StringUtils.isNotBlank(s.getSentiment())){
                    row.createCell(4).setCellValue(s.getSentiment());
                }
                if(StringUtils.isNotBlank(String.valueOf(s.getSimilarityId()))){
                    row.createCell(5).setCellValue(s.getSimilarityId());
                }
                if(StringUtils.isNotBlank(s.getApplyAge())){
                    row.createCell(6).setCellValue(s.getApplyAge());
                }
			}
            try {//下载图片
            	/*HttpHeaders heads = new HttpHeaders();
            	 File fi = new File(path);
            	 heads.setContentDispositionFormData("attachment", path);
            	 heads.setContentType(MediaType.IMAGE_PNG);
            	 return new ResponseEntity<>(FileUtils.readFileToByteArray(fi),heads,HttpStatus.CREATED);*/
				 response.setContentType("application/octet-stream");  
			     response.setHeader("Content-disposition", "attachment;filename=Q&A_export.xls");//默认Excel名称  
			     response.flushBuffer();  
			     wb.write(response.getOutputStream());  
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
    
    public static void main(String[] args) throws IOException {//"F:/10个问答库/情感/感情-刘青.xlsx"
    	OutputStream os = new FileOutputStream("C:/yl.xls");
    	Semantic se = new Semantic();
    	se.setOperatorId(2);
    	List<Semantic> list = semanticService.seletSemantic(se);
    	if(list.isEmpty()){
    		System.out.println(list +"is null");
    		return;
    	}
    	/*List<Map<String,String>> list = new ArrayList<>();
    	Map<String,String> map = new HashMap<>();
    	map.put("filename", "测试");
    	map.put("Artist", "测试2");
    	map.put("title", "导出xls");
    	map.put("Album", "音乐");
    	map.put("size", "1000");
    	list.add(map);*/
    	//PoiUtil.getInstance().exportExcel(list, os);
	}
    
    
    
    
    /**
     * 读取出filePath中的所有数据信息
     * @param filePath excel文件的绝对路径
     * 
     */
    public static void getDataFromExcel(String filePath){
    	//判断是否为excel类型文件
        if(!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx")){
            System.out.println("文件不是excel类型");
        }
        
        FileInputStream fis =null;
        Workbook wookbook = null;
        
        try{
              //获取一个绝对地址的流
              fis = new FileInputStream(filePath);
        }catch(Exception e){
            e.printStackTrace();
        }
       
        try {
            //2003版本的excel，用.xls结尾
            wookbook = new XSSFWorkbook(fis);//得到工作簿 HSSFWorkbook
        } catch (Exception ex) {
            try{
                //2007版本的excel，用.xlsx结尾
                wookbook = new XSSFWorkbook(fis);//得到工作簿XSSFWorkbook
            } catch (IOException e){
                e.printStackTrace();
            }
        }
         
        //得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);
        
        //获得表头
        Row rowHead = sheet.getRow(0);
        
        //判断表头是否正确
        if(rowHead.getPhysicalNumberOfCells() != 2){
            System.out.println("表头的数量不对!");
        }
        
        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        
        //要获得属性
        String text = "";
        String answer = "";
        int latitude = 1;
       
        Semantic se = new Semantic();
        List<Semantic> list = new ArrayList<>();
        
       //获得所有数据
        for(int i = 0 ; i <= totalRowNum ; i++){
            //获得第i行对象
            Row row = sheet.getRow(i);
            if(row == null){
            	System.out.println(row);
            	System.out.println(i);
            	latitude++;
            	continue;
            }
            //获得获得第i行第0列的 String类型对象
            Cell cell = row.getCell((short)0);
            if(cell != null){
            	text = cell.getStringCellValue().toString();
            }else{
            	text = "";
            }
            Cell cell2 = row.getCell((short)1);
            if(cell2 != null ){
            	answer = cell2.getStringCellValue().toString();
            }else{
            	answer = "";
            }
            
            //获得一个数字类型的数据
           
           
          //  se.setId(latitude);
            se.setText(text);
            se.setAnswer(answer);
          //  se.setSimilarityId(se.getId());
            list.add(se);
            
            if("".equals(text) && "".equals(answer)){
            	System.out.println(i  +" jkglkkl");
            	latitude++;
            	continue;
            }
            
            //System.out.println("问："+text+",回复："+answer);
            System.out.println(list);
        }
        try {
        	fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
