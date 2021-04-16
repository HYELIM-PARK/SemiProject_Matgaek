package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import common.Paging;
import dao.face.MemberDao;
import dao.face.RecipeDao;
import dao.impl.MemberDaoImpl;
import dao.impl.RecipeDaoImpl;
import dto.Member;
import dto.Recipe;
import service.face.RecipeService;

public class RecipeServiceImpl implements RecipeService {

	private RecipeDao recipeDao = new RecipeDaoImpl();
	private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = recipeDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		System.out.println("현재 페이지 : " + paging.getCurPage());
		System.out.println("화면에 보여질 페이지 수 : " + paging.getPageCount());
		System.out.println("화면에 보여질 게시글 수 : " + paging.getListCount());
		System.out.println("총 페이지 수 : " + paging.getTotalPage());
		System.out.println("페이지네이션의 시작 번호 : " + paging.getStartPage());
		System.out.println("페이지네이션의 끝 번호 : " + paging.getEndPage());
		System.out.println("화면에 보여질 게시글 시작번호 : " + paging.getStartNo());
		System.out.println("화면에 보여질 게시글 끝번호 : " + paging.getEndNo());
		System.out.println("총 게시글 수 : " + paging.getTotalCount());
		
		
		return paging;
	}
	
	@Override
	public List<Recipe> getRecipeList(Paging paging) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		return recipeDao.selectAll(conn, paging);
	}


	@Override
	public Recipe getRecipe(int post) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( recipeDao.updateViews(conn, post) >= 0) {
			JDBCTemplate.commit(conn);
		}
		
		
		return recipeDao.selectByPostno(conn, post);
	}

	@Override
	public void write(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		Recipe recipe = new Recipe();

		recipe.setTitle( req.getParameter("title") );
		recipe.setInq_content( req.getParameter("content") );

		//작성자id 처리
		Member m = new Member();
		
		m.setUserid((String) req.getSession().getAttribute("userid"));
		
		Member member = new Member();
		
		member.setUserno(memberDao.getUserno(conn, m).getUserno());
		

		if(recipe.getTitle()==null || "".equals(recipe.getTitle())) {
			recipe.setTitle("(제목없음)");
		}

		if( recipeDao.insert(conn, recipe, member) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}



}