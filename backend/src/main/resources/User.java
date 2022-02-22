@Entity
@Table(name = "user")
 public class User {

 	@Id
 	@GenerationType = Identity
 	@NotEmpty
 	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Column(name = "ID_USER")
 	private int ID_USER;
 	
 	@NotEmpty
 	@Length(min=3, max=25)
 	@Column(name = "FULL_NAME")
 	private String FULL_NAME;
 	@NotEmpty
 	@Length(min=8, max=32)
 	@Column(name = "PASSWORD")
 	private String PASSWORD;
 	@NotEmpty
 	@Column(name = "EMAIL")
 	@Email
 	private String EMAIL;
 	@NotEmpty
 	@Column(name = "TEL")
 	private String TEL;
 	@Column(name = "N_SOLD")
 	private int N_SOLD;
 	@Column(name = "N_SELL")
 	private int N_SELL;
 	@Column(name = "IS_ADMIN")
 	private boolean IS_ADMIN; 

 	//CONSTRUCTOR POR DEFECTO
 	public User() {
 		super();
 	}

 	//CONSTRUCTOR 1, TODOS LOS DATOS
 	public User(String FULL_NAME, String PASSWORD, String EMAIL, String TEL, String N_SOLD, String N_SELL, boolean IS_ADMIN) {
 		super();
 		this.FULL_NAME = FULL_NAME;
 		this.PASSWORD = PASSWORD;
 		this.EMAIL = EMAIL;
 		this.TEL = TEL;
 		this.N_SOLD = N_SOLD;
 		this.N_SELL = N_SELL;
 		this.IS_ADMIN = IS_ADMIN;
 	}

 	//CONSTRUCTOR 2, email, password, esAdmin?
 	public User(String PASSWORD, String EMAIL,  boolean IS_ADMIN) {
 		super();
 		this.EMAIL = EMAIL;
 		this.PASSWORD = PASSWORD;
 		this.IS_ADMIN = IS_ADMIN;
 	}
 	
 	
 	

 }
