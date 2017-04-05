package sjtb.e_nci.com.centurypass.module.main.fragment;

import java.util.HashMap;
import java.util.Map;

import infrastructure.base.fragment.BaseFragment;

/**
 * 生成fragment的 工厂类
 * 
 * @author hui
 * 
 */
public class FragmentFactory {

	public static Map<Integer, BaseFragment> map = new HashMap<Integer, BaseFragment>(4);

	public static BaseFragment createFragment(int position) {
		//从缓存中获取fragment对象 如果为空  就重新创建 如果不为空 就直接返回
		BaseFragment fragment = map.get(position);
		if (fragment == null) {
			if (position == 0) {
				fragment = new HomeFragment();
			} else if (position == 1) {
				fragment = new OrderFragment();
			} else if (position == 2) {
				fragment = new NotificationFragment();
			} else if (position == 3) {
				fragment = new PersonalFragment();
			}
			// 第一次进来 先创建 然后缓存
			if (fragment != null) {
				map.put(position, fragment);
			}
		}
		return fragment;
	}
}
