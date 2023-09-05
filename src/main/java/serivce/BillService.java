package serivce;

import models.Bill;
import models.Billdetail;
import models.Schedule;
import models.Ticket;
import utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BillService {
    private final String fileBill = "./data/bills.txt";
    private BillDetailService billDetailService = new BillDetailService();

    public List<Bill> getAllBill() {
        List<Bill> bills = FileUtils.readData(fileBill, Bill.class);
        for (Bill b : bills) {
            List<Billdetail> billdetailList = billDetailService.getAllBillDetailByIDBill(b.getBillID());
            b.setBilldetailList(billdetailList);
        }
        return bills;
    }

    public Bill findBill(long id) {
        List<Bill> bills = getAllBill();
        Bill result = bills.stream().filter(bill -> bill.getBillID() == id).findFirst().orElseThrow(null);
        return result;
    }

    public List<Bill> findBillbyClientID(long id) {
        List<Bill> bills = getAllBill();
        List<Bill> result = bills.stream().filter(bill -> bill.getClientID() == id).collect(Collectors.toList());
        return result;
    }

    public void createBill(Bill bill) {
        List<Bill> bills = getAllBill();
        bills.add(bill);
        FileUtils.writerData(fileBill, bills);
        for (Billdetail b : bill.getBilldetailList()) {
            billDetailService.createBillDetail(b);
        }
    }

    public void deleteBillByID(long id) {
        List<Bill> bills = getAllBill();
        Bill removeBill = bills.stream().filter(bill1 -> bill1.getBillID() == id).findFirst().orElseThrow(null);
        if (removeBill != null) {
            bills.remove(removeBill);
        }
        FileUtils.writerData(fileBill, bills);
        for (Billdetail b : removeBill.getBilldetailList())

            billDetailService.deleteBillDetailByID(b.getBilldetailid());
    }

}
