package Entity;

import java.time.YearMonth;

public class TapChi extends  TaiLieu {
    private String soPhatHanh;
    private YearMonth thangPhatHanh;

    public TapChi() {
    }

    public TapChi(String maTaiLieu, String tenNhaXB, int soBanPhatHanh, String soPhatHanh, YearMonth thangPhatHanh) {
        super(maTaiLieu, tenNhaXB, soBanPhatHanh);
        this.soPhatHanh = soPhatHanh;
        this.thangPhatHanh = thangPhatHanh;
    }

    public String getSoPhatHanh() {
        return soPhatHanh;
    }

    public void setSoPhatHanh(String soPhatHanh) {
        this.soPhatHanh = soPhatHanh;
    }

    public YearMonth getThangPhatHanh() {
        return thangPhatHanh;
    }

    public void setThangPhatHanh(YearMonth thangPhatHanh) {
        this.thangPhatHanh = thangPhatHanh;
    }
}
