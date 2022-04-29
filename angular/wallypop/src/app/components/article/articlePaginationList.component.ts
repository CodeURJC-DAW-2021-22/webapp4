import {Component} from '@angular/core';
import { Article } from 'src/app/models/article.model';
import {ArticleService} from '../../services/article.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'articlePaginationList',
    templateUrl: './articlePaginationList.component.html'
})
export class articlePaginationList {
    constructor(public articleService: ArticleService, public loginService: LoginService) {
    }
    /*
    @GetMapping(params = {"page"})
    public ResponseEntity<List<Article>> articlesPagination(HttpServletRequest request, @RequestParam("page") int page) {
        if (page != -1) { // with pagination
            
            int pageSize = 4;
            try {
                List<Article> lArticlesPageable = new LinkedList<>();
                Pageable paging = PageRequest.of(0, pageSize);
                Page<Article> articlePage;
                articlePage = articleService.findAllPageable(paging.withPage(page));
                if (articlePage.getNumberOfElements() == 0) {
                    // Empty
                } else {
                    for (Article a : articlePage) {
                        lArticlesPageable.add(a);
                    }
                }
                return new ResponseEntity<>(lArticlesPageable, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else { // without pagination
            try {
                return new ResponseEntity<>(articleService.findAll(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
*/

}