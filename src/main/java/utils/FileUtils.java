package utils;

import serivce.IParseModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static <T> List<T> readData(String fileName, Class<T> clazz) {
        List<T> datas = new ArrayList<>();
        FileReader fileReader=null;
        BufferedReader bufferedReader= null;

        try {
            fileReader = new FileReader(fileName);
            bufferedReader= new BufferedReader(fileReader);
            String Line = null;
            while ((Line= bufferedReader.readLine())!=null) {

                //System.out.println(Line);
                //String[] items = Line.split(",");
//                Object obj = null;
//                if(clazz.getName().equals("models.User")){
//                    obj = new User();
//                }if(clazz.getName().equals("models.Product")){
//                    obj = new Product();
//                }
                Object obj = clazz.newInstance();
                IParseModel<T> iParseModel = (IParseModel) obj;
                datas.add(iParseModel.parse(Line));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return datas;
    }
    public static  <T> void writerData(String fileName, List<T> data)  {

        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            for(T item: data){
                fileWriter.write(item.toString()+ "\n");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
