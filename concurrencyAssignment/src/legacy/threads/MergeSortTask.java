package legacy.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MergeSortTask extends RecursiveTask<List<Integer>> {

	private List<Integer> unSorted;
	
	public MergeSortTask(List<Integer> unSorted) {
		this.unSorted = unSorted;
	}
	
	@Override
	protected List<Integer> compute() {
		if(unSorted.size() <= 1){
			return unSorted;
		}
		List<MergeSortTask> subTasks = CreateSubTask();
		for(MergeSortTask subTask : subTasks){
			subTask.fork();
		}
		return merge(subTasks.get(0).join(),subTasks.get(1).join());
	}
	
	private List<Integer> merge(List<Integer> list1, List<Integer> list2){
		List<Integer> list = new ArrayList<>();
		int head1 = 0;
		int head2 = 0;
		while(true){
			if(list1.get(head1) < list2.get(head2)){
				list.add(list1.get(head1));
				head1++;
			}
			else{
				list.add(list2.get(head2));
				head2++;
			}
			if(head1 == list1.size()){
				for(int i=head2;i<list2.size();i++){
					list.add(list2.get(i));
				}
				break;
			}
			if(head2 == list2.size()){
				for(int i=head1;i<list1.size();i++){
					list.add(list1.get(i));
				}
				break;
			}
		}
		return list;
	}
	
	private List<MergeSortTask> CreateSubTask(){
		List<MergeSortTask> subTasks = new ArrayList<>();
		
		int mid = unSorted.size()/2;
		
		subTasks.add(new MergeSortTask(unSorted.subList(0, mid)));
		subTasks.add(new MergeSortTask(unSorted.subList(mid, unSorted.size())));
		
		return subTasks;
	}

}
