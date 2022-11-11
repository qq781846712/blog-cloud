import {ref} from "vue";
import dayjs from "dayjs";
import {ElMessageBox} from "element-plus";
import {message, Switch} from "@pureadmin/components";

export function useColumns() {
    const switchLoadMap = ref({});

    const columns = ref([
        {
            type: "selection",
            width: 55,
            hide: ({checkList}) => !checkList.includes("勾选列")
        },
        {
            label: "序号",
            type: "index",
            width: 70,
            hide: ({checkList}) => !checkList.includes("序号列")
        },
        {
            label: "角色编号",
            prop: "roleId"
        },
        {
            label: "角色名称",
            prop: "roleName"
        },
        {
            label: "角色标识",
            prop: "roleKey"
        },
        {
            label: "显示顺序",
            prop: "roleSort"
        },
        {
            label: "状态",
            prop: "status",
            width: 130,
            cellRenderer: scope => (
                <Switch
                    size={scope.props.size === "small" ? "small" : "default"}
                    loading={switchLoadMap.value[scope.index]?.loading}
                    v-model:checked={scope.row.status}
                    checkedValue={0}
                    unCheckedValue={1}
                    checked-children="已开启"
                    un-checked-children="已关闭"
                    onChange={() => onChange(scope)}
                />
            )
        },
        {
            label: "创建时间",
            width: 180,
            prop: "createTime",
            formatter: ({createTime}) =>
                dayjs(createTime).format("YYYY-MM-DD HH:mm:ss")
        },
        {
            label: "操作",
            fixed: "right",
            width: 180,
            slot: "operation"
        }
    ]);

    function onChange({row, index}) {
        ElMessageBox.confirm(
            `确认要<strong>${
                row.status === 0 ? "启用" : "停用"
            }</strong><strong style='color:var(--el-color-primary)'>${
                row.name
            }</strong>角色吗?`,
            "系统提示",
            {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
                dangerouslyUseHTMLString: true,
                draggable: true
            }
        )
            .then(() => {
                switchLoadMap.value[index] = Object.assign(
                    {},
                    switchLoadMap.value[index],
                    {
                        loading: true
                    }
                );
                setTimeout(() => {
                    switchLoadMap.value[index] = Object.assign(
                        {},
                        switchLoadMap.value[index],
                        {
                            loading: false
                        }
                    );
                    message.success("已成功修改角色状态");
                }, 300);
            })
            .catch(() => {
                row.status === 0 ? (row.status = 1) : (row.status = 0);
            });
    }

    return {
        columns
    };
}