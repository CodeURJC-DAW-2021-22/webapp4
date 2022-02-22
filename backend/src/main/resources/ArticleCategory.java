@Entity
@Table(name = "ArticleCategory")
 public class ArticleCategory {

 	@Id
 	@GenerationType = Identity
 	@NonNull
 	@Column(name = "ID_ARTICLE_CATEGORY")
 	private int ID_ARTICLE_CATEGORY;
 	
 	@ManyToMany
 	@NonNull
 	@Column(name = "ID_ARTICLE")
 	private int ID_ARTICLE;
 	
 	//@FALTA CARDINALIDAD
 	@NonNull
 	@Column(name = "ID_CATEGORY")
 	private int ID_CATEGORY;

 	//CONSTRUCTOR POR DEFECTO
 	public ArticleCategory() {

 	}

 	//CONSTRUCTOR 1, TODOS LOS DATOS
 	public ArticleCategory() {
 		super();
 		this.ID_ARTICLE = ID_ARTICLE;
 		this.ID_CATEGORY = ID_CATEGORY;	
 	} 	
}	
