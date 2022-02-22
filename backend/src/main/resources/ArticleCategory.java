@Entity
@Table(name = "articleCategory")
 public class ArticleCategory {

 	@Id
 	@GenerationType = Identity
 	@NotEmpty
 	@Column(name = "ID_ARTICLE_CATEGORY")
 	private int ID_ARTICLE_CATEGORY;
 	
 	@ManyToMany
 	@NotEmpty
 	@Column(name = "ID_ARTICLE")
 	private int ID_ARTICLE;
 	
 	//@FALTA CARDINALIDAD
 	@NotEmpty
 	@Column(name = "ID_CATEGORY")
 	private int ID_CATEGORY;

 	//CONSTRUCTOR POR DEFECTO
 	public ArticleCategory() {
 		super();
 	}

 	//CONSTRUCTOR 1, TODOS LOS DATOS
 	public ArticleCategory() {
 		super();
 		this.ID_ARTICLE = ID_ARTICLE;
 		this.ID_CATEGORY = ID_CATEGORY;	
 	} 	
}	
