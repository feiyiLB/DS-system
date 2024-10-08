<script>
import logo1 from '@/assets/logo1.png'
export default {
  name: "Chat",
  data(){
    return{
      user: JSON.parse(localStorage.getItem('honey-user') || '{}'),
      inputContents:'',
      contentList:[],
      logo1:logo1,

    }
  },
  methods:{
    onSend(){
      const userQuestion = this.inputContents;

      if (!userQuestion.trim()) {
        return;  // Don't send if the input is empty
      }

      // Add the user's question to the contentList immediately
      const userMessage = {
        question: userQuestion,
        answer: "Waiting for AI response...",  // Placeholder until we get the actual answer
        time: new Date().toISOString().slice(0, 19).replace('T', ' ')  // Format current time
      };
      this.contentList.push(userMessage);

      // Clear the input after sending
      this.inputContents = '';

      this.$request.post('/chat/add', {
        question: userQuestion
      })
          .then(response => {
            // Assuming the backend returns a 200 code and the updated chat object with the answer
            if (response.code === '200') {
              const returnedChat = response.data;  // This should contain the question, answer, and time

              // Find the last message and update the answer
              this.contentList[this.contentList.length - 1].answer = returnedChat.answer;
            } else {
              console.error('Error sending message:', response.msg);
            }
          })
          .catch(error => {
            console.error('Error:', error);
            // Update the answer with error message if there's a failure
            this.contentList[this.contentList.length - 1].answer = 'Error sending message';
          });
    },
    fetchChatHistory() {
      // Replace the URL with your backend API endpoint
      this.$request.get('/chat/getChat')
          .then(res => {
            // Check if the response is successful
            if (res.code === '200') {
              // Store the chat history in contentList
              this.contentList = res.data;
            } else {
              this.$message.error('Error fetching chat history:', res.msg);
            }
          })
          .catch(error => {
            console.error('Error:', error);
          });
    },

  },
  mounted() {
    // Make the API call when the component is mounted
    this.fetchChatHistory();
  },
}
</script>

<template>
<div class="home-view">
  <div class="chat-panel">
    <div class="message-panel">
      <div class="message-title">
        <span class="w-150px mb-2" >
          AI Chat
        </span>
      </div>
        <div class="message-list">
          <div class="message-list-item" v-for="item in contentList" :key="item.id">
            <div class="message-list-item-right" v-if="item.question">
              <div class="message">
                <el-card>
                  <div class="message-text">
                    {{item.question}}
                  </div>
                </el-card>
              </div>
              <div>
                <img :src="user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" alt="" style="width: 50px; height: 50px; border-radius: 50%; margin: 0 5px">

              </div>
            </div>
            <div class="message-list-item-left" v-if="item.answer">
              <div>
                <el-avatar :src="logo1"></el-avatar>
              </div>
              <div class="message">
                <el-card>
                  <div>
                    {{item.answer}}
                  </div>
                </el-card>
              </div>

            </div>
          </div>
        </div>


      <div class="input-box">
        <div class="send">
          <el-input v-model="inputContents" placeholder="Input anything you like" style="border-radius: 10px 0 0 10px;"
                    :autosize="{ minRows: 1, maxRows: 4 }" type="textarea" resize="none" @keydown.enter.native="onSend"
                    class="input_chat">
          </el-input>
          <el-tooltip class="box-item">
            <el-button color="#62a7ef" style="border-radius: 0 10px 10px 0;color: #fff; background-color:  rgb(2,19,39)" @click="onSend"
            >发送</el-button>
          </el-tooltip>

        </div>
        <p class="input-desc">服务生成的所有内容均由人工智能模型生成，其生成内容的准确性和完整性无法保证，不代表我们的态度或观点</p>
      </div>
    </div>
  </div>
</div>
</template>

<style scoped lang="scss">
.home-view {
  height:89vh;


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

      display: flex;
      flex-direction: column;
      width: 2000px;
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
        width: 100px;
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
        box-sizing: border-box;
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
            .el-avatar img {
              width: 60px; /* 设置图片宽度 */
              height: 60px; /* 设置图片高度 */
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