package uz.pdp.appwerehouse.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String massage;
    private boolean success;

    private Object object;

    public Result(String massage, boolean success) {
        this.massage = massage;
        this.success = success;
    }
}
