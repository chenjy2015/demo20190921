package com.example.myapplication;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.drakeet.multitype.ItemViewBinder;
import com.drakeet.multitype.JavaClassLinker;
import com.drakeet.multitype.MultiTypeAdapter;
import com.drakeet.multitype.Type;
import com.example.baselibrary.ui.BaseUIActivity;
import com.example.myapplication.databinding.ActivityMultiTypeBinding;
import com.example.myapplication.multi.Occupation;
import com.example.myapplication.multi.People;
import com.example.myapplication.multi.Sex;
import com.example.myapplication.multi.Student;
import com.example.myapplication.multi.StudentViewBinder;
import com.example.myapplication.multi.StudentViewBinder2;
import com.example.myapplication.multi.Teacher;
import com.example.myapplication.multi.TeacherViewBinder;
import com.example.myapplication.viewmodel.LoginViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeActivity extends BaseUIActivity<ActivityMultiTypeBinding, LoginViewModel> {

    List<People> peoples = new ArrayList<>();

    MultiTypeAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_multi_type;
    }

    @Override
    public void init() {
        Student student = new Student("小明", 8, Sex.BOY.sex, "写作业", Occupation.STUDENT.occupation);
        Student student2 = new Student("小红", 9, Sex.GIRL.sex, "写作业", Occupation.STUDENT.occupation);
        Student student3 = new Student("小黄", 10, Sex.BOY.sex, "写作业", Occupation.STUDENT.occupation);
        Student student4 = new Student("小蓝", 11, Sex.GIRL.sex, "写作业", Occupation.STUDENT.occupation);
        Student student5 = new Student("小绿", 12, Sex.NOT_MAN.sex, "打学生", Occupation.STUDENT.occupation);
        Student student6 = new Student("小胡", 8, Sex.BOY.sex, "写作业", Occupation.STUDENT.occupation);
        Student student7 = new Student("小青", 9, Sex.GIRL.sex, "写作业", Occupation.STUDENT.occupation);
        Student student8 = new Student("小紫", 10, Sex.BOY.sex, "写作业", Occupation.STUDENT.occupation);
        Student student9 = new Student("小灰", 11, Sex.GIRL.sex, "写作业", Occupation.STUDENT.occupation);
        Student student10 = new Student("小白", 12, Sex.NOT_MAN.sex, "打学生", Occupation.STUDENT.occupation);

        Teacher teacher = new Teacher("刘老师", 25, Sex.MAN.sex, "批作业", Occupation.TEACHER.occupation);
        Teacher teacher2 = new Teacher("李老师", 26, Sex.WOMAN.sex, "批作业", Occupation.TEACHER.occupation);
        Teacher teacher3 = new Teacher("陈老师", 27, Sex.MAN.sex, "批作业", Occupation.TEACHER.occupation);
        Teacher teacher4 = new Teacher("张老师", 28, Sex.WOMAN.sex, "批作业", Occupation.TEACHER.occupation);
        Teacher teacher5 = new Teacher("王老师", 29, Sex.NOT_MAN.sex, "打老师", Occupation.PROFESSOR.occupation);
        Teacher teacher6 = new Teacher("艾老师", 25, Sex.MAN.sex, "批作业", Occupation.TEACHER.occupation);
        Teacher teacher7 = new Teacher("秦老师", 26, Sex.WOMAN.sex, "批作业", Occupation.TEACHER.occupation);
        Teacher teacher8 = new Teacher("孙老师", 27, Sex.MAN.sex, "批作业", Occupation.TEACHER.occupation);
        Teacher teacher9 = new Teacher("钱老师", 28, Sex.WOMAN.sex, "批作业", Occupation.TEACHER.occupation);
        Teacher teacher10 = new Teacher("周老师", 29, Sex.NOT_MAN.sex, "打老师", Occupation.PROFESSOR.occupation);

        peoples.add(student);
        peoples.add(student2);
        peoples.add(student3);
        peoples.add(student4);
        peoples.add(student5);
        peoples.add(student6);
        peoples.add(student7);
        peoples.add(student8);
        peoples.add(student9);
        peoples.add(student10);

        peoples.add(teacher);
        peoples.add(teacher2);
        peoples.add(teacher3);
        peoples.add(teacher4);
        peoples.add(teacher5);
        peoples.add(teacher6);
        peoples.add(teacher7);
        peoples.add(teacher8);
        peoples.add(teacher9);
        peoples.add(teacher10);

        mAdapter = new MultiTypeAdapter();
        //数据类型不同
//        mAdapter.register(Student.class, new StudentViewBinder());
        mAdapter.register(Teacher.class, new TeacherViewBinder());
        //同样数据做区分
        mAdapter.register(Student.class).to(new StudentViewBinder(), new StudentViewBinder2()).withJavaClassLinker(new JavaClassLinker<Student>() {
            @NotNull
            @Override
            public Class<? extends ItemViewBinder<Student, ?>> index(int i, Student student) {
                if (student.getSex().equals(Sex.NOT_MAN.sex)) {
                    return StudentViewBinder2.class;
                } else {
                    return StudentViewBinder.class;
                }
            }
        });
        dataBinding.recycler.setAdapter(mAdapter);
        mAdapter.setItems(peoples);
        mAdapter.notifyDataSetChanged();
        dataBinding.recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void initEvent() {

    }
}
