@Entity
@Table(name = "Report")
 public class Report {

 	@Id
 	@GenerationType = Identity
 	@NonNull
 	@Column(name = "ID_REPORT")
 	private int ID_REPORT;
 	
 	@ManyToOne
 	@Column(name = "ID_ARTICLE")
 	private int ID_ARTICLE;
 	@NonNull
 	@Column(name = "EMAIL")
 	private String EMAIL;
 	@NonNull
 	@Column(name = "DESCRIPTION")
 	private String DESCRIPTION;
 	@Column(name = "PHOTO_BLOB")
 	private img PHOTO_BLOB; 

 	//CONSTRUCTOR POR DEFECTO
 	public Report() {
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
