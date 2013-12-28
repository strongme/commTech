/**
 * Created with JetBrains WebStorm.
 * User: walter
 * Date: 13-11-23
 * Time: 上午11:41
 * To change this template use File | Settings | File Templates.
 */
var subjectList = [
    {
        id: 'yw',
        name: '语文'
    },
    {
        id: 'sx',
        name: '数学'
    },
    {
        id: 'english',
        name: '英语'
    },
    {
        id: 'wl',
        name: '物理'
    },
    {
        id: 'hx',
        name: '化学'
    },
    {
        id: 'sw',
        name: '生物'
    },
    {
        id: 'zz',
        name: '政治'
    },
    {
        id: 'ls',
        name: '历史'
    },
    {
        id: 'dl',
        name: '地理'
    },
    {
        id: 'music',
        name: '音乐'
    },
    {
        id: 'pe',
        name: '体育'
    },
    {
        id: 'ms',
        name: '美术'
    },
    {
        id: 'xl',
        name: '心理'
    },
    {
        id: 'xxjs',
        name: '信息技术'
    },
    {
        id: 'tyjs',
        name: '通用技术'
    }
];
var questionData = [
    {
        name: {
            left: '师德师风',
            right: '满意：4~5分 一般：2~3分 不满意：0~1分'
        },
        question:[
            {
                id: 1,
                content: '关爱学生，不体罚、不辱骂学生',
                min: 0,
                max:5
            },
            {
                id: 2,
                content: '为人师表，着装大方、得体',
                min: 0,
                max:5
            },
            {
                id: 3,
                content: '按时上课，不迟到、不早退，上课不接打手机',
                min: 0,
                max:5
            },
            {
                id: 4,
                content: '不利用工作之便谋取私利 ',
                min: 0,
                max:5
            }
        ]
    },
    {
        name: {
            left: '课堂教学',
            right: '满意：15~20分 一般：8~14分 不满意：0~7分'
        },
        question:[
            {
                id: 5,
                content: '重点突出，条理清晰，教学效果好',
                min: 0,
                max:20
            },
            {
                id: 6,
                content: '善于启发，激发学习兴趣，教学方法恰当',
                min: 0,
                max:20
            },
            {
                id: 7,
                content: '善于管理，课堂纪律好，气氛和谐',
                min: 0,
                max:20
            }
        ]
    },
    {
        name: {
            left: '作业批改',
            right: '第8题 满意：5~6分 一般：2~4分 不满意：0~1分|第9、10题 满意：5~7分 一般：2~4分 不满意：0~1'
        },
        question:[
            {
                id: 8,
                content: '精选作业，能督促学生按时完成作业',
                min: 0,
                max:6
            },
            {
                id: 9,
                content: '认真批改作业，及时讲解、反馈',
                min: 0,
                max:7
            },
            {
                id: 10,
                content: '耐心回答学生提出的问题',
                min: 0,
                max:7
            }
        ]
    }
];

var questionCount = 10;