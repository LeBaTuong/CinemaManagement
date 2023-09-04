package serivce;

import models.Billdetail;
import models.Schedule;
import utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BillDetailService {
    private final String fileBilldetail = "./data/billdetails.txt";
    public List<Billdetail> getAllBillDetail(){
        List<Billdetail> billdetails = FileUtils.readData(fileBilldetail, Billdetail.class);
        return billdetails;
    }
    public List<Billdetail> getAllBillDetailByIDBill(long id){
        List<Billdetail> billdetails = getAllBillDetail();
        List<Billdetail> result = billdetails.stream().filter(billdetail -> billdetail.getBillId()==id).collect(Collectors.toList());
        return result;
    }
    public void createBillDetail (Billdetail billdetail){
        List<Billdetail> billdetails = getAllBillDetail();
        billdetails.add(billdetail);
        FileUtils.writerData(fileBilldetail, billdetails);

    }
}
