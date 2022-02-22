@Entity
@Table(name = "report")
 public class Report {

 	@Id
 	@GenerationType = Identity
 	@NotEmpty
 	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Column(name = "ID_REPORT")
 	private int ID_REPORT;
 	
 	@ManyToOne
 	@Column(name = "ID_ARTICLE")
 	private int ID_ARTICLE;
 	@NotEmpty
 	@Column(name = "EMAIL")
 	@Email
 	private String EMAIL;
 	@NotEmpty
 	@Length(min=0, max=256)
 	@Column(name = "DESCRIPTION")
 	private String DESCRIPTION;
 	@Column(name = "PHOTO_BLOB")
 	private img PHOTO_BLOB; 

 	//CONSTRUCTOR POR DEFECTO
 	public Report() {
 		super();
 	}

 	//CONSTRUCTOR 1, TODOS LOS DATOS
 	public Report(int ID_ARTICLE, String EMAIL, String DESCRIPTION, img PHOT_BLOB) {
 		super();
 		this.ID_ARTICLE = ID_ARTICLE;
 		this.EMAIL = EMAIL;
 		this.DESCRIPTION = DESCRIPTION;
 		this.PHOT_BLOB = PHOT_BLOB; 		
 	} 	
 }
