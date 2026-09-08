package Entity;

import java.time.LocalDate;

public class Bao extends TaiLieu{
    private LocalDate ngayPhatHanh;

    public Bao() {
    }

    public Bao(String maTaiLieu, String tenNhaXB, int soBanPhatHanh, LocalDate ngayPhatHanh) {
        super(maTaiLieu, tenNhaXB, soBanPhatHanh);
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public LocalDate getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(LocalDate ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }
}
