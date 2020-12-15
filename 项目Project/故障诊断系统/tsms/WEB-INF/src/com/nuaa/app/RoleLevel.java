/**
 * 
 */
package com.nuaa.app;

/**
 * @author mahao
 *
 */
public class RoleLevel {
	/*
	 * 状态对应级别 此处配置级别
	 */
	private final static int TechLevel=7;//工艺员
	private final static int TechManagerLevel=2;//工艺管理员
	private final static int AdminLevel=1;//系统管理员
	private final static int WorkerLevel=8;//操作人员
	private final static int AuditorLevel=6;//工艺审核员--级别6
	private final static int ReauditorLevel=5;//工艺复审员--级别5
	private final static int CheckerLevel=4;//工艺标检员--级别4
	private final static int RatifierLevel=3;//工艺批准员--级别3
	
	/*
	 * 是否处于审批流程中
	 */
	public static boolean isFlow(int usrlevel){
		if(usrlevel==AuditorLevel || usrlevel==ReauditorLevel || usrlevel==CheckerLevel || usrlevel==RatifierLevel){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * 是否工艺审核员
	 */
	public static boolean isAuditor(int usrlevel){
		return usrlevel == AuditorLevel ? true : false;
	}

	/*
	 * 是否工艺复审员
	 */
	public static boolean isReAuditor(int usrlevel){
		return usrlevel == ReauditorLevel ? true : false;
	}

	/*
	 * 是否工艺标检员
	 */
	public static boolean isChecker(int usrlevel){
		return usrlevel == CheckerLevel ? true : false;
	}

	/*
	 * 是否工艺批准员
	 */
	public static boolean isRatifier(int usrlevel){
		return usrlevel == RatifierLevel ? true : false;
	}

	/*
	 * 是否系统管理员
	 */
	public static boolean isAdmin(int usrlevel){
		return usrlevel == AdminLevel ? true : false;
	}

	/*
	 * 是否工艺员
	 */
	public static boolean isTech(int usrlevel){
		return usrlevel == TechLevel ? true : false;
	}

	/*
	 * 是否工艺管理员
	 */
	public static boolean isTechManager(int usrlevel){
		return usrlevel == TechManagerLevel ? true : false;
	}
	
	/*
	 * 是否操作人员
	 */
	public static boolean isWorker(int usrlevel){
		return usrlevel == WorkerLevel ? true : false;
	}
	/*
	public static void main(String[] args){
		System.out.println(RoleLevel.isAdmin(AdminLevel));//true;
		System.out.println(RoleLevel.isAdmin(WorkerLevel));//false;
	}
	*/
	
	/*
	 * 根据角色级别获得审核通过后的状态
	 */
	public static int getFlowPassState(int userlevel){
		if(userlevel == AuditorLevel){
			return FileState.REAUDIT;
		}else if(userlevel == ReauditorLevel){
			return FileState.CHECK;
		}else if(userlevel == CheckerLevel){
			return FileState.RATIFY;
		}else if(userlevel == RatifierLevel){
			return FileState.PASS;
		}else if(userlevel == TechLevel){
			return FileState.AUDIT;
		}else{
			return FileState.EDIT;
		}
	}
	
	/*
	 * 根据角色级别获得驳回后的状态
	 */
	public static int getUnPassState(int userlevel){
		return FileState.UNPASS;
	}
	
	/*
	 * 
	 */
	public static int getCurState(int userlevel){
		if(userlevel == AuditorLevel){
			return FileState.AUDIT;
		}else if(userlevel == ReauditorLevel){
			return FileState.REAUDIT;
		}else if(userlevel == CheckerLevel){
			return FileState.CHECK;
		}else if(userlevel == RatifierLevel){
			return FileState.RATIFY;
		}else if(userlevel == TechLevel){
			return FileState.EDIT;
		}else{
			return FileState.EDIT;
		}		
	}
}
