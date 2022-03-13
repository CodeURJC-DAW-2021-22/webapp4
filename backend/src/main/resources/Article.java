@Entity
@Table(name = "Article")
 public class Article {

 	@Id
 	@GenerationType = Identity
 	@NonNull
 	@Column(name = "ID_ARTICLE")
 	private int ID_ARTICLE;
 	
 	@ManyToOne
 	@NonNull
 	@Column(name = "ID_USER")
 	private int ID_USER;
 	@NonNull
 	@Column(name = "TITLE")
 	private String TITLE;
 	@Column(name = "DESCRIPTION")
 	private String DESCRIPTION;
 	@NonNull
 	@Column(name = "PRICE")
 	private float PRICE;
 	@NonNull
 	@Column(name = "DATE")
 	private date DATE;
 	@Column(name = "RESERVED_BIT")
 	private boolean RESERVED_BIT;
 	@Column(name = "SOLD_BIT")
 	private boolean SOLD_BIT;
 	@Column(name = "PHOTO_BLOB")
 	private img PHOTO_BLOB; 
 	@Column(name = "END_VISITED")
 	private int END_VISITED;

 	//CONSTRUCTOR POR DEFECTO
 	public Article() {
 		this.RESERVED_BIT = 0;
 		this.SOLD_BIT = 0;
 		this.END_VISITED = 0;
 	}

 	//CONSTRUCTOR 1, TODOS LOS DATOS
 	public Article(int ID_USER, String TITLE, String DESCRIPTION, String PRICE, date DATE, boolean RESERVED_BIT, boolean SOLD_BIT, img PHOT_BLOB, int END_VISITED) {
 		super();
 		this.ID_USER = ID_USER;
 		this.TITLE = TITLE;
 		this.DESCRIPTION = DESCRIPTION;
 		this.PRICE = PRICE;
 		this.DATE = DATE;
 		this.RESERVED_BIT = RESERVED_BIT;
 		this.SOLD_BIT = SOLD_BIT;
 		this.PHOT_BLOB = PHOT_BLOB;
 		this.END_VISITED= END_VISITED
 		
 	} 	
 }
