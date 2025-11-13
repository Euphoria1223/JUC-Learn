package day04;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ----- AlbertEu
 * @since ----- 2025/11/12
 * description:
 */
public class StreamTest {
    /*
     * 题目要求：一分钟内完成此题，只能用一行代码实现！
     * 现在有5个用户！筛选：
     * 1、ID 必须是偶数
     * 2、年龄必须大于23岁
     * 3、用户名转为大写字母
     * 4、用户名字母倒着排序
     * 5、只输出一个用户！
     */
    public static void main(String[] args) {
        User u1 = new User(1,"a",21);
        User u2 = new User(2,"b",22);
        User u3 = new User(3,"c",23);
        User u4 = new User(4,"d",24);
        User u5 = new User(6,"e",25);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        List<User> userList = list.stream()
                .filter(user -> user.getId() % 2 == 0)
                .filter(user -> user.getAge() > 23)
                .map(user -> new User(user.getId(), user.getName().toUpperCase(), user.getAge()))
                .sorted((user1, user2) -> user2.getName().compareTo(user1.getName()))
                .limit(1)
                .collect(Collectors.toList());
        System.out.println(userList);

    }

}
@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
    private Integer id;
    private String name;
    private Integer age;
}
