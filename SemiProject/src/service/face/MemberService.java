package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Member;

public interface MemberService {

	/**
	 * 로그인 정보 저장 id,pw
	 * @param req
	 * @return
	 */
	public Member getLoginMember(HttpServletRequest req);

	/**
	 * 로그인 인증 
	 * @param member
	 * @return
	 */
	public boolean login(Member member);

	public Member info(Member member);
	/**
	 * 회원가입 정보저장
	 * @param req
	 * @return
	 */
	public Member getJoinMember(HttpServletRequest req);

	/**
	 * 저장된 회원정보 가입
	 * @param member - 저장된 회원정보
	 */
	public void join(Member member);
	/**
	 * 아이디 중복 체크
	 * @param parameter
	 * @return
	 */
	public int idcheck(String usrid);
	/**
	 * 닉네임 중복 체크
	 * @param parameter
	 * @return
	 */

	public int nickcheck(String nick);
	/**
	 * 유저 아이디 저장
	 * @param attribute
	 * @return
	 */
	public Member saveLoinId(Object attribute);
	/**
	 * 유저 정보 불러오기
	 * @param member
	 * @return
	 */
	public Member getuserInfo(Member member);

	public List<Member> getUserdata();

	/**
	 * 수정 정보 저장 객체
	 * @param req
	 * @return
	 */
	public Member getUpdateMember(HttpServletRequest req);

	/**
	 * 유저 정보 수정
	 * @param member
	 */
	public void updateMemberInfo(Member member);

	/**
	 * 유저 정보 삭제
	 * @param attribute
	 */
	public void secession(Object attribute);

	/**
	 * 아이디 찾기
	 * @param req
	 * @return
	 */
	public Member findId(HttpServletRequest req);

	/**
	 * 비밀번호 찾기
	 * @param req
	 * @return
	 */
	public Member findPw(HttpServletRequest req);

	/**
	 * 이메일, 유저아이디 저장
	 * @param req
	 * @param tempPW 
	 * @return
	 */
	public Member saveEmail(HttpServletRequest req, String tempPW);

	/**
	 * 비밀번호 변경
	 * @param member
	 */
	public void chagePW(Member member);


	
	
	

}
