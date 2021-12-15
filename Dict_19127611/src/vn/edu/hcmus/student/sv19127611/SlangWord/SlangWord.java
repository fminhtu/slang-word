package vn.edu.hcmus.student.sv19127611.SlangWord;

/**
 * vn.edu.hcmus.student.sv19127611.SlangWord
 * Created by fminhtu
 * Date 12/15/2021 - 4:35 PM
 * Description: ...
 */
public class SlangWord {
    private final String slang;
    private final String meaning;

    public SlangWord(String slang, String meaning) {
        this.slang = slang;
        this.meaning = meaning;
    }

    public SlangWord() {
        slang = "";
        meaning = "";
    }

    public String getSlang() {
        return slang;
    }

    public String getMeaning() {
        return meaning;
    }
}
