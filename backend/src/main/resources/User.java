@Entity
@Table(name = "User")
 public class User {

 	@Id
 	@GenerationType = Identity
 	@NonNull
 	@Column(name = "ID_USER")
 	private int ID_USER;
 	
 	@NonNull
 	@Column(name = "FULL_NAME")
 	private String FULL_NAME;
 	@NonNull
 	@Column(name = "PASSWORD")
 	private String PASSWORD;
 	@NonNull
 	@Column(name = "EMAIL")
 	private String EMAIL;
 	@NonNull
 	@Column(name = "TEL")
 	private String TEL;
 	@Column(name = "N_SOLD")
 	private int N_SOLD;
 	@Column(name = "N_SELL")
 	private int N_SELL;
 	@Column(name = "IS_ADMIN")
 	private boolean IS_ADMIN; 

 	//CONSTRUCTOR POR DEFECTO
 	public User() {}

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
