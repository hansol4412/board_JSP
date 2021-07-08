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
		boardItemDao.viewCnt(bno, viewCnt+1); // ��ȸ�� 1�� ����
	}

	@Override
	public Page pagination(String pagenoP, String criteria, String findWord, String boardId) {
		int pageno = toInt(pagenoP);

		if(pageno<1) {// ���� ������
			pageno = 1;
		}

		int total_record = 0;
		
		if(criteria== null || findWord == null) {
			total_record=boardItemDao.getTotalCount(boardId); // �� ���ڵ� ��
			if(total_record==0) {
				total_record = 1;
			}
		} else {
			total_record=boardItemDao.findTotalCount(criteria, findWord, boardId);
			if(total_record==0) {
				total_record = 1;
			}
		}
		
		int page_per_record_cnt = 10; // ������ �� ���ڵ� ��
		int group_per_page_cnt = 10; // ������ �� ������ ��ȣ ��

		int record_end_no = pageno * page_per_record_cnt; // �� ������ ������ ��
		int record_start_no = record_end_no - (page_per_record_cnt - 1); // �� ������ ������ ����
		if(record_end_no>total_record)
		{
			record_end_no = total_record;
		}

		int total_page = total_record / page_per_record_cnt + (total_record % page_per_record_cnt > 0 ? 1 : 0); // �� ������ ��
		if(pageno>total_page)
		{
			pageno = total_page;
		}

		int group_no = pageno / group_per_page_cnt + (pageno % group_per_page_cnt > 0 ? 1 : 0);
		// ���� �׷��ȣ = ���������� / �������� ������ ��ȣ�� (���� ������ % �������� ������ ��ȣ �� >0 ? 1:0)

		int page_eno = group_no * group_per_page_cnt;
		// ���� �׷� �� ��ȣ = ���� �׷��ȣ * �������� ������ ��ȣ
		int page_sno = page_eno - (group_per_page_cnt - 1);
		// ���� �׷� ���� ��ȣ = ���� �׷� �� ��ȣ - (�������� ������ ��ȣ �� -1)

		if(page_eno>total_page)
		{
			// ���� �׷� �� ��ȣ�� ��ü������ �� ���� Ŭ ���
			page_eno = total_page;
			// ���� �׷� �� ��ȣ�� = ��ü������ ���� ����
		}

		int prev_pageno = page_sno - group_per_page_cnt; // ���� ������ ��ȣ = ���� �׷� ���� ��ȣ - �������� ������ ��ȣ��

		int next_pageno = page_sno + group_per_page_cnt; // ���� ������ ��ȣ = ���� �׷� ���� ��ȣ + �������� ������ ��ȣ��

		if(prev_pageno<1) {
			// ���� ������ ��ȣ�� 1���� ���� ���
			prev_pageno = 1;
			// ���� �������� 1��
		}
		
		if(next_pageno>total_page){ // ���� ���������� ��ü������ ������ Ŭ���
			next_pageno = total_page / group_per_page_cnt * group_per_page_cnt + 1;
			// ���� ������ = ��ü�������� / �������� ������ ��ȣ�� * �������� ������ ��ȣ�� + 1
		}
		
		Page page = new Page(pageno, prev_pageno, page_sno, page_eno, next_pageno);
		return page;
	}
	
	//pagination���� �ÿ��ϴ� �޼ҵ�
	public int toInt(String x) {
		int a = 0;
		try {
			a = Integer.parseInt(x);
		} catch (Exception e) {
		}
		return a;
	}

}
