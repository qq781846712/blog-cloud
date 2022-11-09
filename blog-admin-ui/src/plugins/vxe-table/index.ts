import "xe-utils";
import "./index.scss";
import {App} from "vue";
import "font-awesome/css/font-awesome.min.css";

import {
    Button,
    Checkbox,
    CheckboxGroup,
    Colgroup,
    Column,
    Edit,
    Export,
    Filter,
    Form,
    FormGather,
    FormItem,
    Grid,
    Icon,
    Input,
    Keyboard,
    List,
    Menu,
    Modal,
    Optgroup,
    Option,
    Pager,
    Pulldown,
    Radio,
    RadioButton,
    RadioGroup,
    Select,
    Switch,
    Table,
    Textarea,
    Toolbar,
    Tooltip,
    Validator,
    VXETable
} from "vxe-table";

// 全局默认参数
VXETable.setup({
    size: "medium",
    version: 0,
    zIndex: 1002,
    table: {
        // 自动监听父元素的变化去重新计算表格
        autoResize: true,
        // 鼠标移到行是否要高亮显示
        highlightHoverRow: true
    },
    input: {
        clearable: true
    }
});

export function useTable(app: App) {
    app
        .use(Icon)
        .use(Filter)
        .use(Edit)
        .use(Menu)
        .use(Export)
        .use(Keyboard)
        .use(Validator)
        // 可选组件
        .use(Column)
        .use(Colgroup)
        .use(Grid)
        .use(Tooltip)
        .use(Toolbar)
        .use(Pager)
        .use(Form)
        .use(FormItem)
        .use(FormGather)
        .use(Checkbox)
        .use(CheckboxGroup)
        .use(Radio)
        .use(RadioGroup)
        .use(RadioButton)
        .use(Switch)
        .use(Input)
        .use(Select)
        .use(Optgroup)
        .use(Option)
        .use(Textarea)
        .use(Button)
        .use(Modal)
        .use(List)
        .use(Pulldown)
        // 安装表格
        .use(Table);
}
