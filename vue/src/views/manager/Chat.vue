<script>
export default {
  name: "Chat",
  data(){
    return{
      user: JSON.parse(localStorage.getItem('honey-user') || '{}'),
      inputContents:'',

    }
  },
  methods:{
    onSend(){

    }
  }
}
</script>

<template>
<div class="home-view">
  <div class="chat-panel">
    <div class="message-panel">
      <div class="message-title">
        <el-text class="w-150px mb-2" line-clamp="1" truncated size="large">
          智能聊天
        </el-text>
      </div>
        <div class="message-list">
          <div class="message-list-item">
            <div class="message-list-item-right">
              <div class="message">
                <el-card>
                  <div class="message-text">
                    questionaaaaaaaaaaa
                  </div>
                </el-card>
              </div>
              <div>
                <el-avatar :size="50" :src="user.avatar" />
              </div>
            </div>
            <div class="message-list-item-left">
              <div>
                <el-avatar ></el-avatar>
              </div>
              <div class="message">
                <el-card>
                  <div>
                    answer
                  </div>
                </el-card>
              </div>

            </div>
          </div>
        </div>


      <div class="input-box">
        <div class="send">
          <el-input v-model="inputContents" placeholder="请输入内容" style="border-radius: 10px 0 0 10px;"
                    :autosize="{ minRows: 1, maxRows: 4 }" type="textarea" resize="none" @keydown.enter.native="onSend"
                    class="input_chat">
          </el-input>
          <el-tooltip class="box-item" :content="sendStatus ? '请稍后提问' : '请输入您的问题'" placement="top-start"
                      :disabled="inputContents.trim() !== ''">
            <el-button color="#62a7ef" style="border-radius: 0 10px 10px 0;color: #fff;" @click="onSend"
            >发送</el-button>
          </el-tooltip>

        </div>
        <p class="input-desc">服务生成的所有内容均由人工智能模型生成，其生成内容的准确性和完整性无法保证，不代表我们的态度或观点</p>
      </div>
    </div>
  </div>
</div>
</template>

<style scoped lang="css">
.home-view {
  width: 100%;
  height: 100%;


  .chat-panel {
    height: 100%;
    display: flex;
    border-radius: 20px;
    background-color: white;
    overflow: hidden;

    .dialogue-list {
      width: 23%;
      min-width: 200px;
      padding: 20px 10px;
      box-sizing: border-box;

      .dialogu-but {
        display: flex;

        justify-content: center;
        margin-bottom: 20px;
        height: 50px;
        box-sizing: border-box;
      }

      .session-list {
        height: 100%;

        .session-item {

          padding: 12px;
          background-color: white;

          border-radius: 10px;
          position: relative;
          cursor: grab;
          overflow: hidden;
          margin: 10px;
          box-sizing: border-box;


          .count-time {
            margin-top: 10px;
            font-size: 10px;
            display: flex;
            justify-content: space-between;
          }

          /* 当处于激活状态时增加蓝色描边 */


        }
      }

    }

    /* 右侧消息记录面板*/
    .message-panel {
      height: 100%;
      width: 100%;


      display: flex;
      flex-direction: column;

      padding: 10px;
      box-sizing: border-box;

      .message-title {
        text-align: center;
        padding: 10px;
        border-bottom: 1px solid #eee;

        display: flex;
        align-items: center;
        justify-content: space-between;

        span {
          flex: 1;
          text-align: center;
        }
      }

      .arrow {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        background-color: #f5f5f5;
        position: absolute;
        bottom: 20px;
        right: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
      }



      .w-150px {
        width: 150px;
        /* 确保文本框的宽度 */
        margin-bottom: 2px;
        /* 文本框下方的边距，如果需要 */
      }

      .message-list {
        max-width: 100%;
        min-width: 30%;
        height: 95%;
        display: flex;
        flex-direction: column;

        overflow-y: scroll;

        .message-list-item {
          display: flex;
          align-items: flex-start;
          flex-direction: column;

          margin: 0 10px;

          .message-list-item-left,
          .message-list-item-right {
            max-width: 80%;
            margin: 20px;
            display: flex;
            word-break: break-all;

            .message {

              margin: 10px 10px;


              .message-content {
                font-size: 20px;
              }
            }
          }

          .message-list-item-right {
            align-self: flex-end;
            justify-content: flex-end;

            .message {

              .message-text {
                word-spacing: normal;

              }
            }
          }

        }
      }


      .message-content {
        width: 100%;
        height: 5%;
        display: flex;
        align-items: center;
        justify-content: center;
      }

      .input-box {
        width: 100%;
        margin-top: 10px;

        .send {

          display: flex;
          align-items: flex-end;
          justify-content: center;


        }
      }

      .input-desc {
        color: #8f91a8;
        font-size: 10px;
        height: 12px;
        line-height: 12px;
        overflow: hidden;
        text-align: center;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 100%;
        margin-top: 8px;
      }
    }

  }



  .input_chat .el-textarea__inner {

    color: red
  }
}
</style>