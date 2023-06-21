package model;

import java.util.List;

import dao.MutterDAO;

public class PostMutterLogic {

	public void execute(Mutter mutter, List<Mutter> mutterList){

		// 先頭に情報を追加
		mutterList.add(0, mutter);
	}

	public void execute(Mutter mutter){

		MutterDAO dao = new MutterDAO();
		dao.create(mutter);

	}
}
