@Entity
@Table(name = "Category")
 public class Category {

 	@Id
 	@GenerationType = Identity
 	@NonNull
 	@Column(name = "ID_ARTICLE")
 	private int ID_ARTICLE;
 	
 	@NonNull
 	@Column(name = "TITLE")
 	private String TITLE;
 	@Column(name = "DESCRIPTION")
 	private String DESCRIPTION;
 	@Column(name = "PHOTO_BLOB")
 	private img PHOTO_BLOB; 

 	//CONSTRUCTOR POR DEFECTO
 	public Category() {

 	}

 	//CONSTRUCTOR 1, TODOS LOS DATOS
 	public Category(int ID_USER, String TITLE, String DESCRIPTION, img PHOTO_BLOB) {
 		super();
 		this.ID_USER = ID_USER;
 		this.TITLE = TITLE;
 		this.DESCRIPTION = DESCRIPTION;
 		this.PHOT_BLOB = PHOT_BLOB;
 		
 	} 	
}	