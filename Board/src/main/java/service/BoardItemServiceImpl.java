package service;

import java.util.List;

import dao.BoardItemDao;
import dao.BoardItemDaoImpl;
import domain.BoardItem;
import domain.Page;

public class BoardItemServiceImpl implements BoardItemService{
	BoardItemDao boardItemDao = new BoardItemDaoImpl();
	@Override
	public List<BoardItem> getListWithPaging(String pageno, String boardId) {
		Page pageProcess = this.pagination(pageno, null, null, boardId);
		
		int pageNum = ((pageProcess.getPageno()-1)*10);

		return boardItemDao.getListWithPaging(pageNum+"", boardId);
	}

	@Override
	public List<BoardItem> findListWithPaging(String pageno, String criteria, String findWord, String boardId) {
		Page pageProcess = this.pagination(pageno, criteria, findWord, boardId);
		int pageNum = ((pageProcess.getPageno()-1)*10);
		return boardItemDao.findListWithPaging(pageNum+"", criteria, findWord, boardId);
	}

	@Override
	public BoardItem read(int bno) {
		return boardItemDao.read(bno);
	}

	@Override
	public void insert(BoardItem board) {
		boardItemDao.insert(board);
	}

	@Override
	public void update(BoardItem board) {
		boardItemDao.update(board);
	}

	@Override
	public void delete(BoardItem board) {
		boardItemDao.delete(board);
	}

	@Override
	public int getTotalCount(String boardId) {
		return boardItemDao.getTotalCount(boardId);
	}

	@Override
	public int findTotalCount(String criteria, String findWord, String boardId) {
		return boardItemDao.findTotalCount(criteria, findWord, boardId);
	}

	@Override
	public void viewCnt(int bno, int viewCnt) {
		boardItemDao.viewCnt(bno, viewCnt+1); // 조회수 1씩 증가
	}

	@Override
	public Page pagination(String pagenoP, String criteria, String findWord, String boardId) {
		int pageno = toInt(pagenoP);

		if(pageno<1) {// 현재 페이지
			pageno = 1;
		}

		int total_record = 0;
		
		if(criteria== null || findWord == null) {
			total_record=boardItemDao.getTotalCount(boardId); // 총 레코드 수
			if(total_record==0) {
				total_record = 1;
			}
		} else {
			total_record=boardItemDao.findTotalCount(criteria, findWord, boardId);
			if(total_record==0) {
				total_record = 1;
			}
		}
		
		int page_per_record_cnt = 10; // 페이지 당 레코드 수
		int group_per_page_cnt = 10; // 페이지 당 보여줄 번호 수

		int record_end_no = pageno * page_per_record_cnt; // 한 페이지 내에서 끝
		int record_start_no = record_end_no - (page_per_record_cnt - 1); // 한 페이지 내에서 시작
		if(record_end_no>total_record)
		{
			record_end_no = total_record;
		}

		int total_page = total_record / page_per_record_cnt + (total_record % page_per_record_cnt > 0 ? 1 : 0); // 총 페이지 수
		if(pageno>total_page)
		{
			pageno = total_page;
		}

		int group_no = pageno / group_per_page_cnt + (pageno % group_per_page_cnt > 0 ? 1 : 0);
		// 현재 그룹번호 = 현재페이지 / 페이지당 보여줄 번호수 (현재 페이지 % 페이지당 보여줄 번호 수 >0 ? 1:0)

		int page_eno = group_no * group_per_page_cnt;
		// 현재 그룹 끝 번호 = 현재 그룹번호 * 페이지당 보여줄 번호
		int page_sno = page_eno - (group_per_page_cnt - 1);
		// 현재 그룹 시작 번호 = 현재 그룹 끝 번호 - (페이지당 보여줄 번호 수 -1)

		if(page_eno>total_page)
		{
			// 현재 그룹 끝 번호가 전체페이지 수 보다 클 경우
			page_eno = total_page;
			// 현재 그룹 끝 번호와 = 전체페이지 수를 같게
		}

		int prev_pageno = page_sno - group_per_page_cnt; // 이전 페이지 번호 = 현재 그룹 시작 번호 - 페이지당 보여줄 번호수

		int next_pageno = page_sno + group_per_page_cnt; // 다음 페이지 번호 = 현재 그룹 시작 번호 + 페이지당 보여줄 번호수

		if(prev_pageno<1) {
			// 이전 페이지 번호가 1보다 작을 경우
			prev_pageno = 1;
			// 이전 페이지를 1로
		}
		
		if(next_pageno>total_page){ // 다음 페이지보다 전체페이지 수보가 클경우
			next_pageno = total_page / group_per_page_cnt * group_per_page_cnt + 1;
			// 다음 페이지 = 전체페이지수 / 페이지당 보여줄 번호수 * 페이지당 보여줄 번호수 + 1
		}
		
		Page page = new Page(pageno, prev_pageno, page_sno, page_eno, next_pageno);
		return page;
	}
	
	//pagination에서 시용하는 메소드
	public int toInt(String x) {
		int a = 0;
		try {
			a = Integer.parseInt(x);
		} catch (Exception e) {
		}
		return a;
	}

}
