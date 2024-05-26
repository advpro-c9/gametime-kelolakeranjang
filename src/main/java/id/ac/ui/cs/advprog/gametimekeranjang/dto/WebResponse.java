package id.ac.ui.cs.advprog.gametimekeranjang.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebResponse<T> {
    private T data;
    private String errors;

    @Override
    public String toString() {
        return "WebResponse(data=" + data + ", errors=" + errors + ")";
    }
}