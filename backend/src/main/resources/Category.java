@Entity
@Table(name = "category")
 public class Category {

 	@Id
 	@GenerationType = Identity
 	@NotEmpty
 	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Column(name = "ID_CATEGORY")
 	private int ID_ARTICLE;
 	
 	@NotEmpty
 	@Length(min=5, max=24)
 	@Column(name = "TITLE")
 	private String TITLE;
 	@Length(min=0, max=256)
 	@Column(name = "DESCRIPTION")
 	private String DESCRIPTION;
 	@Column(name = "PHOTO_BLOB")
 	private img PHOTO_BLOB; 

 	//CONSTRUCTOR POR DEFECTO
 	public Category() {
 		super();
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