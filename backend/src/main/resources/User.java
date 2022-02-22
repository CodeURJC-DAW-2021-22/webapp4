@Entity
 public class User {

 	@Id
 	@GeneratedValue(strategy = GenerationType.AUTO)
 	private Long ID_USER;

 	private String FULL_NAME;
 	private String PASSWORD;
 	private String EMAIL;
 	private String TEL;
 	private int N_SOLD;
 	private int N_SELL;
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
