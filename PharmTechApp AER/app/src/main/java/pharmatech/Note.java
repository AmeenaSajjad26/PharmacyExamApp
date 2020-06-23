//Ameena
//Bonface
//Eve

package pharmatech;

// --------------------- Class to hold notes methods ---------------------
public class Note {
    private String idNote;
    private String aboutNote;
    private String dateNote;
    private String contentNote;

    public Note(String idNote, String aboutNote, String dateNote, String contentNote) {
        this.idNote = idNote;
        this.aboutNote = aboutNote;
        this.dateNote = dateNote;
        this.contentNote = contentNote;
    }
    public Note() {

    }
    public Note(String aboutNote,String contentNote){

        this.aboutNote = aboutNote;
        this.contentNote = contentNote;
    }
    public Note(String idNote,String aboutNote,String contentNote){
        this.idNote = idNote;
        this.aboutNote = aboutNote;
        this.contentNote = contentNote;
    }

    public String getIdNote() {
        return idNote;
    }

    public void setIdNote(String idNote) {
        this.idNote = idNote;
    }

    public String getAboutNote() {
        return aboutNote;
    }

    public void setAboutNote(String aboutNote) {
        this.aboutNote = aboutNote;
    }

    public String getDateNote() {
        return dateNote;
    }

    public void setDateNote(String dateNote) {
        this.dateNote = dateNote;
    }

    public String getContentNote() {
        return contentNote;
    }

    public void setContentNote(String contentNote) {
        this.contentNote = contentNote;
    }
}
