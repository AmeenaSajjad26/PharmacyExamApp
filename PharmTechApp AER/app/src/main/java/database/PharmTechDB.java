//Ameena
//Bonface
//Eve

package database;

public class PharmTechDB {
    public static final String noteTableName="Note";
    public static final class DrugsTable {
        public static final String nameDrugTab = "AllDrugs";

        public static final class ColsDrugs {
            public static final String GenericName = "Generic_Name";
            public static final String BrandName = "Brand_Name";
            public static final String Purpose = "Purpose";
            public static final String DEASch = "DEA_Sch";
            public static final String Special = "Special";
            public static final String StudyTopic = "Study_Topic";
            public static final String Categorie = "Category";
        }
    }
    public static final class NoteTable {
        public static final String noteTableName="Note2";
        public static final class ColsNote {
            public static final String id="id";
            public static final String aboutCol = "about";
            public static final String dateCol = "date";
            public static final String contentCol = "content";

        }
    }
}
