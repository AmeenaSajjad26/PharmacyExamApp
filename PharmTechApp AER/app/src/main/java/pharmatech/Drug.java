//Ameena
//Bonface
//Eve

package pharmatech;

public class Drug {

    private String GenericName;
    private String BrandName;
    private String Purpose;
    private String DEASch;
    private String Special;
    private String StudyTopic;
    private String Category;
    private String id_med;

    // constructors
    public Drug() {
    }

    public Drug(String GenericName, String BrandName, String Purpose, String use) {
        this.GenericName = GenericName;
        this.BrandName = BrandName;
        this.StudyTopic = Purpose;
        this.DEASch = use;
    }


    public Drug(String id_med,String genericName, String brandName, String purpose, String DEASch,String special, String category,String studyTopic) {
        this.GenericName = genericName;
        this.BrandName = brandName;
        this.Purpose = purpose;
        this.Special = special;
        this.DEASch = DEASch;
        this.StudyTopic = studyTopic;
        this.Category = category;
        this.id_med = id_med;
    }

    public String description(String cont) {
        if (this.getGenericName() == null)
            return "";
        else
            return "Generic Name: "  + this.GenericName
                + "\nBrand Name: "   + this.BrandName
                + "\nDEA Schedule: " + this.getDEASch()
                + "\nPurpose: "      + this.getPurpose()
                + "\nStudy topic: "  + this.getStudyTopic()
                + "\nCategory: "     + this.getCategory()
                + "\nSpecial: "      + this.getSpecial()
                + "\nNote: "         + cont ;
    }

    // getters
    public String getGenericName() {
        return GenericName;
    }

    public String getBrandName() {
        return BrandName;
    }

    public String getStudyTopic() {
        return StudyTopic;
    }

    public String getDEASch() {
        return DEASch;
    }

    public String getCategory() {
        return Category;
    }

    public String getSpecial() {
        return Special;
    }

    public String getPurpose() {
        return Purpose;
    }

    public String getId_med() {
        return id_med;
    }

    // setters
    public void setGenericName(String genericName) {
        this.GenericName = genericName;
    }

    public void setBrandName(String brandName) {
        this.BrandName = brandName;
    }

    public void setStudyTopic(String studyTopic) {
        this.StudyTopic = studyTopic;
    }

    public void setDEASch(String DEASch) {
        this.DEASch = DEASch;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public void setSpecial(String special) {
        Special = special;
    }

}
